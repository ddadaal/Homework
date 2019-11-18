use crate::item::Item;
use crate::item::KnownItem;
use crate::utils::find_item;

pub fn ls(item: KnownItem, path: String) -> Result<(), &'static str> {
    let root = find_item(item, path.clone());

    match root {
        Some(item) => {
            if item.is_dir() {
                ls_rec(&item, path);
                Ok(())
            } else {
                Err("Specified path is a directory.")
            }
        }
        None => Err("Specified path does not exist."),
    }
}

fn ls_rec<'a>(item: &impl Item<'a>, parent: String) {
    println!("{}:", parent);
    let sub_item_iter = item
        .sub_items_iter()
        .expect("Attempt to find sub items of a file.");

    for item in sub_item_iter {
        item.print();
        print!("  ");
    }
    println!();
    for item in item.sub_items_iter().unwrap() {
        if item.is_dir && !item.is_special {
            ls_rec(&item, format!("{}{}/", parent, item.name));
        }
    }
}
