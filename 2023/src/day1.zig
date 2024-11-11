const std = @import("std");
const print = std.debug.print;
const assert = std.debug.assert;
const mem = std.mem;
const Allocator = mem.Allocator;
const isDigit = std.ascii.isDigit;
const ArrayList = std.ArrayList;
const helper = @import("helpers.zig");

const words_n_nums = [_][]const u8{
    "one",
    "two",
    "three",
    "four",
    "five",
    "six",
    "seven",
    "eight",
    "nine",
};

fn get_digit_in_words(text: []const u8) ?u8 {
    var digit: ?u8 = null;
    switch (text[0]) {
        'o', 't', 'f', 's', 'e', 'n' => {
            for (words_n_nums, 1..) |word, ix| {
                if (std.mem.startsWith(u8, text, word)) {
                    digit = @intCast(ix);
                    return digit;
                }
            }
        },
        else => {},
    }
    return digit;
}

fn find_caliberation(input: []const u8) struct { u64, u64 } {
    var splits = mem.splitScalar(u8, input, '\n');
    var total_p1: u64 = 0;
    var total_p2: u64 = 0;
    while (splits.next()) |v| {
        var left_num_p1: ?u32 = null;
        var right_num_p1: ?u32 = null;
        var left_num_p2: ?u32 = null;
        var right_num_p2: ?u32 = null;
        for (0..v.len) |i| {
            var num: ?u32 = null;
            var num_word: ?u32 = null;

            if (isDigit(v[i])) {
                num = v[i] - 48;
            } else if (get_digit_in_words(v[i..])) |n| {
                num_word = @intCast(n);
            } else {}

            if (num) |d| {
                if (left_num_p1 == null) {
                    left_num_p1 = d;
                }
                if (left_num_p2 == null) {
                    left_num_p2 = d;
                }
                right_num_p1 = d;
                right_num_p2 = d;
            }
            if (num_word) |d| {
                if (left_num_p2 == null) {
                    left_num_p2 = d;
                }
                right_num_p2 = d;
            }
        }
        total_p1 += (left_num_p1 orelse 0) * 10 + (right_num_p1 orelse 0);
        total_p2 += (left_num_p2 orelse 0) * 10 + (right_num_p2 orelse 0);
    }
    return .{ total_p1, total_p2 };
}

pub fn solve() !void {
    const page_allocator = std.heap.page_allocator;
    if (helper.load_input_file(page_allocator, 1)) |f| {
        defer page_allocator.free(f);
        const result: struct { u64, u64 } = find_caliberation(f);
        std.debug.print("Day 1 : \n", .{});
        std.debug.print(" - Part 1 : {d}\n", .{result[0]});
        std.debug.print(" - Part 2 : {d}\n", .{result[1]});
    } else |err| {
        std.debug.print("\nsome error {s}", .{@errorName(err)});
    }
}

test "Day 1 Part 1" {
    const part1_sample_input =
        \\1abc2
        \\pqr3stu8vwx
        \\a1b2c3d4e5f
        \\treb7uchet
    ;

    assert(find_caliberation(part1_sample_input)[0] == 142);
}

test "Day 1 Part 2" {
    const part2_sample_input =
        \\two1nine
        \\eightwothree
        \\abcone2threexyz
        \\xtwone3four
        \\4nineeightseven2
        \\zoneight234
        \\7pqrstsixteen
    ;

    assert(find_caliberation(part2_sample_input)[1] == 281);
}
