pub mod merge_sort;
pub mod parallel_merge_sort;

use crate::merge_sort::merge_sort;
use crate::parallel_merge_sort::parallel_merge_sort;

use rand::Rng;
use std::time::Instant;
use std::sync::{Arc, Mutex};

fn main() {
    // change the size of the array
    let size: usize = 1000000;

    let mut arr: Vec<i32> = (0..size)
        .map(|_| rand::thread_rng().gen_range(1..size as i32))
        .collect();

    
    let start_time = Instant::now();
    merge_sort(&mut arr);
    let normalmergesorttime = start_time.elapsed();

    let arr_arc = Arc::new(Mutex::new(arr));
    let start_time = Instant::now();
    parallel_merge_sort(Arc::clone(&arr_arc));
    let parallelmergesorttime = start_time.elapsed(); 

    println!("Normal Merge Sort completed in {:.2?}", normalmergesorttime);
    println!("Parallel Merge Sort completed in {:.2?}", parallelmergesorttime);
}
