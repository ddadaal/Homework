use crate::item::{Item, KnownItem};

pub fn read_to_int(data: &[u8], mut offset: usize, length: u32) -> u32 {
    let mut result: u32 = 0;
    for i in 0..length {
        result += (data[offset] as u32) << (i * 8);
        offset += 1;
    }
    result
}
pub fn find_item<'a>(root: KnownItem<'a>, mut path: String) -> Option<KnownItem<'a>> {
    fn rec<'b>(root: KnownItem<'b>, paths: &[&str]) -> Option<KnownItem<'b>> {
        if paths.len() == 0 {
            return Some(root);
        }

        for item in root.sub_items_iter()? {
            if item.full_name == paths[0] {
                return rec(KnownItem::FileItem(item), &paths[1..]);
            }
        }

        None
    };

    normalize_path(&mut path);
    rec(root, &split_path(&path))
}

pub fn normalize_path(path: &mut String) {
    if !path.starts_with('/') {
        path.insert(0, '/');
    }

    if !path.ends_with('/') {
        path.insert(path.len(), '/');
    }
}

pub fn split_path(path: &str) -> Vec<&str> {
    path.split('/')
        .filter(|x| x.len() > 0)
        .collect::<Vec<&str>>()
}
