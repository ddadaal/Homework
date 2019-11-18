use crate::file_item::FileItem;
use crate::item::Item;
use crate::item::KnownItem;
use crate::utils::find_item;
use crate::utils::normalize_path;

pub fn count(root: KnownItem, mut path: String) -> Result<(), &'static str> {
    let root = match find_item(root, path.clone()) {
        Some(x) => x,
        None => return Err("Specified path does not exist."),
    };

    if !root.is_dir() {
        print!("{}: 1 file, 0 dir", path);
    } else {
        normalize_path(&mut path);
        count_rec(root, path, 1);
    }

    Ok(())
}

fn count_rec(root: KnownItem, path: String, indent: u32) {
    print!("{}: ", path);

    let sub_items = root
        .sub_items_iter()
        .expect("Fail to get all file items.")
        .collect::<Vec<FileItem>>();

    let mut file_count = 0;
    let mut dir_count = 0;

    for item in sub_items.iter() {
        if item.is_dir && !item.is_special {
            dir_count += 1;
        } else if !item.is_dir {
            file_count += 1;
        }
    }

    println!("{} file(s), {} dir(s)", file_count, dir_count);

    for item in sub_items.into_iter() {
        if item.is_dir && !item.is_special {
            print!("{}", " ".repeat(indent as usize * 2));
            let new_path = format!("{}{}/", path, item.name);
            count_rec(KnownItem::FileItem(item), new_path, indent + 1)
        }
    }
}
