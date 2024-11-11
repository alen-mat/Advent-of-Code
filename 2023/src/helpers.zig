const std = @import("std");
const Allocator = std.mem.Allocator;

pub fn load_input_file(allocator: Allocator, day: u8) ![]const u8 {
    const base_path = "/home/alen/workspace/aoc/2023/resources/";
    var resource_dir = try std.fs.openDirAbsolute(base_path, .{});
    defer resource_dir.close();
    const file_name = try std.fmt.allocPrint(allocator, "day_{d}.txt", .{day});
    // std.debug.print("\n reading file {s\n}", .{file_name});
    defer allocator.free(file_name);
    var file = try resource_dir.openFile(file_name, .{});
    defer file.close();

    const stat = try file.stat();
    const buff = try file.readToEndAlloc(allocator, stat.size);
    return buff;
}
