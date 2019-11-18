use crate::item::Item;
use crate::utils::read_to_int;
use std::borrow::Cow;

pub struct FileItem<'a> {
    pub name: &'a str,
    pub ext: &'a str,
    pub is_special: bool,
    pub is_dir: bool,
    pub write_time: u32,
    pub write_date: u32,
    pub content_pointer: &'a [u8],
    pub size: u32,
    pub data_sector: &'a [u8],
    pub fat_sector: &'a [u8],
    pub start_cluster: u32,

    // full_name of a dir is just its name, not requiring allocation
    // but full_name of a file is a new string {name}.{ext}, which needs allocation
    // Cow is the perfect container for such situation.
    pub full_name: Cow<'a, str>,
}

impl<'a> FileItem<'a> {
    fn find_end(s: &[u8], start: usize, end: usize) -> &str {
        // use the unsafe version to avoid the validation check of str::from_utf8
        unsafe {
            std::str::from_utf8_unchecked(
                &s[start
                    ..s[start..end]
                        .iter()
                        .position(|&x| x == b' ')
                        .unwrap_or(end - start)
                        + start],
            )
        }
    }

    pub fn new(start: &'a [u8], data_sector: &'a [u8], fat_sector: &'a [u8]) -> Self {
        let start_cluster = read_to_int(start, 0x1A, 2);
        let content_pointer_offset = if start_cluster == 0 {
            0
        } else {
            start_cluster - 2
        };

        let is_dir = read_to_int(start, 0xB, 1) == 0x10;

        let name = FileItem::find_end(start, 0, 8);
        let ext = FileItem::find_end(start, 8, 11);

        let full_name = if is_dir {
            Cow::Borrowed(name)
        } else {
            Cow::Owned(format!("{}.{}", name, ext))
        };

        FileItem {
            is_dir,
            write_time: read_to_int(start, 0x16, 2),
            write_date: read_to_int(start, 0x18, 2),
            size: read_to_int(start, 0x1C, 4),
            start_cluster,
            content_pointer: &data_sector[(content_pointer_offset as usize) * 512..],
            is_special: start[0] == b'.',
            name,
            ext,
            data_sector,
            fat_sector,
            full_name,
        }
    }

    pub fn print(&self) {
        if self.is_dir {
            print!("\x1b[0;31m{}\x1b[0m", self.name);
        } else {
            print!("{}", self.full_name);
        }
    }

    pub fn read_content(&self) -> Result<String, &'static str> {
        let mut s = String::new();

        let mut cluster = self.start_cluster as usize;
        while cluster < 0xFF8 {
            if cluster >= 0xFF0 {
                return Err("Bad cluster encountered");
            }
            let content = &self.data_sector[(cluster - 2) * 512..];
            s += match std::str::from_utf8(&content[0..512]) {
                Ok(s) => s,
                Err(_) => return Err("Error while reading content"),
            };

            // get next cluster no
            let bytes = read_to_int(self.fat_sector, cluster / 2 * 3, 3) as usize;

            cluster = if cluster % 2 == 1 {
                bytes >> (3 * 4)
            } else {
                bytes & 0x000FFF
            }
        }

        Ok(s[..self.size as usize].into())
    }
}

impl<'a> Item<'a> for FileItem<'a> {
    type SubItemIterator = FileItemIterator<'a>;
    fn sub_items_iter(&self) -> Option<Self::SubItemIterator> {
        if self.is_dir {
            Some(FileItemIterator::new(
                self.content_pointer,
                self.data_sector,
                self.fat_sector,
            ))
        } else {
            None
        }
    }
    fn is_dir(&self) -> bool {
        self.is_dir
    }
}

pub struct FileItemIterator<'a> {
    content_pointer: &'a [u8],
    data_sector: &'a [u8],
    fat_sector: &'a [u8],
    current: usize,
}

impl<'a> Iterator for FileItemIterator<'a> {
    type Item = FileItem<'a>;

    fn next(&mut self) -> Option<Self::Item> {
        if self.content_pointer[self.current * 0x20] != 0 {
            self.current += 1;
            Some(FileItem::new(
                &self.content_pointer[(self.current - 1) * 0x20..],
                self.data_sector,
                self.fat_sector,
            ))
        } else {
            None
        }
    }
}

impl<'a> FileItemIterator<'a> {
    fn new(content_pointer: &'a [u8], data_sector: &'a [u8], fat_sector: &'a [u8]) -> Self {
        FileItemIterator {
            content_pointer,
            data_sector,
            fat_sector,
            current: 0,
        }
    }
}
