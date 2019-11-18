use crate::item::Item;
use crate::item::KnownItem;
use crate::utils::find_item;

pub fn cat(root: KnownItem, path: String) -> Result<(), &'static str> {
    match find_item(root, path) {
        None => Err("Specified path does not exist."),
        Some(item) => {
            if item.is_dir() {
                Err("Specified is a dir.")
            } else {
                match item {
                    KnownItem::Image(_) => panic!("It should not happen"),
                    KnownItem::FileItem(file) => {
                        println!("{}", file.read_content()?);
                        Ok(())
                    }
                }
            }
        }
    }
}
