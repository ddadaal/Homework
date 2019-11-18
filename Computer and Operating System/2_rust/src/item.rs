use crate::file_item::FileItem;
use crate::file_item::FileItemIterator;
use crate::image::Image;
use crate::image::ImageIterator;

pub trait Item<'a> {
    type SubItemIterator: Iterator<Item = FileItem<'a>>;
    fn sub_items_iter(&self) -> Option<Self::SubItemIterator>;
    fn is_dir(&self) -> bool;
}

pub enum KnownItem<'a> {
    FileItem(FileItem<'a>),
    Image(&'a Image<'a>),
}

impl<'a> Item<'a> for KnownItem<'a> {
    type SubItemIterator = KnownItemIterator<'a>;

    fn is_dir(&self) -> bool {
        match self {
            KnownItem::FileItem(i) => i.is_dir(),
            KnownItem::Image(i) => i.is_dir(),
        }
    }

    fn sub_items_iter(&self) -> Option<Self::SubItemIterator> {
        match self {
            KnownItem::FileItem(i) => i.sub_items_iter().map(|x| KnownItemIterator::FileItem(x)),
            KnownItem::Image(i) => i.sub_items_iter().map(|x| KnownItemIterator::Image(x)),
        }
    }
}

pub enum KnownItemIterator<'a> {
    FileItem(FileItemIterator<'a>),
    Image(ImageIterator<'a>),
}

impl<'a> Iterator for KnownItemIterator<'a> {
    type Item = FileItem<'a>;

    fn next(&mut self) -> Option<Self::Item> {
        match self {
            KnownItemIterator::FileItem(iter) => iter.next(),
            KnownItemIterator::Image(iter) => iter.next(),
        }
    }
}
