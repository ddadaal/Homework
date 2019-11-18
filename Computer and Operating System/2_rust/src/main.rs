mod cat;
mod count;
mod file_item;
mod image;
mod item;
mod ls;
mod utils;

use crate::image::Image;
use crate::item::KnownItem;
use crate::ls::ls;
use std::fs::read;
use std::io::stdin;
use std::io::stdout;
use std::io::Write;

fn main() -> std::io::Result<()> {
    // read content onto a stack allocated arr
    // 1.44M is too much for stack-allocated arr, resulting in overflow
    // let mut file = File::open("a.img")?;
    // let mut buf = [0 as u8; 1440 * 1024];
    // file.read(&mut buf[..])?;
    // let image = image::Image::new(&buf);

    // Can use the read function to read contents into a Vec
    // but it has to allocate memory on heap :(
    let content = read("a.img").expect("Unable to read a.img");
    let image = Image::new(&content);

    let mut buf = String::new();
    loop {
        print!("> ");
        stdout().flush()?;
        if let Err(err) = read_and_interpret(&image, &mut buf) {
            if err == "Exit" {
                break Ok(());
            }
            print!("{}", err);
        }
        buf.clear();
        println!();
        println!();
    }
}

fn read_and_interpret<'a>(image: &'a Image<'a>, buf: &mut String) -> Result<(), &'static str> {
    stdin().read_line(buf).expect("Error reading command.");
    let cmds = buf.split_whitespace().collect::<Vec<&str>>();

    let known_item = KnownItem::Image(&image);

    if cmds.len() == 0 {
        return Ok(());
    }

    match cmds[0] {
        "ls" => {
            if cmds.len() == 1 {
                ls(known_item, "/".into())
            } else if cmds.len() > 2 {
                Err("Too many arguments. Expect 0-1 argument")
            } else {
                ls(known_item, cmds[1].into())
            }
        }
        "cat" => {
            if cmds.len() == 1 {
                Err("Input a file path.")
            } else if cmds.len() > 2 {
                Err("Too many arguments. Expect 1 argument.")
            } else {
                cat::cat(known_item, cmds[1].into())
            }
        }
        "count" => {
            if cmds.len() == 1 {
                count::count(known_item, "/".into())
            } else if cmds.len() > 2 {
                Err("Too many arguments. Expect 0-1 argument.")
            } else {
                count::count(known_item, cmds[1].into())
            }
        }
        "exit" => Err("Exit"),
        _ => Err("Unknown command."),
    }
}
