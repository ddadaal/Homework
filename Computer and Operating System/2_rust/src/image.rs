use crate::file_item::FileItem;
use crate::item::Item;
use crate::utils::read_to_int;

pub struct Image<'a> {
    root_dir_sector: &'a [u8],
    data_sector: &'a [u8],
    fat_sector: &'a [u8],
}

impl<'a> Image<'a> {
    pub fn new(content: &'a [u8]) -> Self {
        let root_dir_sector = &content[19 * 512..];

        let fat_sector = &content[1 * 512..];

        let bpb_root_ent_cnt = read_to_int(content, 17, 2);
        let bpb_byte_per_sec = read_to_int(content, 11, 2);
        let root_dir_sectors =
            ((bpb_root_ent_cnt * 32) + (bpb_byte_per_sec - 1)) / bpb_byte_per_sec; // 14
        let data_sector = &content[(19 + root_dir_sectors as usize) * 512..];

        Image {
            root_dir_sector,
            data_sector,
            fat_sector,
        }
    }
}

impl<'a> Item<'a> for Image<'a> {
    type SubItemIterator = ImageIterator<'a>;
    fn is_dir(&self) -> bool {
        true
    }
    fn sub_items_iter(&self) -> Option<Self::SubItemIterator> {
        Some(ImageIterator::new(
            self.root_dir_sector,
            self.data_sector,
            self.fat_sector,
        ))
    }
}

pub struct ImageIterator<'a> {
    root_dir_sector: &'a [u8],
    data_sector: &'a [u8],
    fat_sector: &'a [u8],
    current: usize,
}

impl<'a> Iterator for ImageIterator<'a> {
    type Item = FileItem<'a>;
    fn next(&mut self) -> Option<Self::Item> {
        if self.root_dir_sector[self.current * 0x20] != 0 {
            self.current += 1;
            Some(FileItem::new(
                &self.root_dir_sector[(self.current - 1) * 0x20..],
                self.data_sector,
                self.fat_sector,
            ))
        } else {
            None
        }
    }
}

impl<'a> ImageIterator<'a> {
    fn new(root_dir_sector: &'a [u8], data_sector: &'a [u8], fat_sector: &'a [u8]) -> Self {
        ImageIterator {
            root_dir_sector,
            data_sector,
            fat_sector,
            current: 0,
        }
    }
}
