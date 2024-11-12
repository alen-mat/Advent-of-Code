const std = @import("std");
const helper = @import("helpers.zig");

const Cubes = struct {
    red: i32 = 0,
    blue: i32 = 0,
    green: i32 = 0,
};
const Round = struct { gameID: i32 = 0, reveled: Cubes };

//12 red cubes, 13 green cubes, and 14 blue cubes
fn game_point(input: []const u8) struct { i32, i32 } {
    var game_min: i32 = 0;
    var game_total: i32 = 0;
    var lines = std.mem.splitScalar(u8, input, '\n');
    while (lines.next()) |line| {
        const round = line_parser(line);
        // std.debug.print("Game {d} - red :{d}, green :{d} ,blue: {d}\n", .{ round.gameID, round.reveled.red, round.reveled.green, round.reveled.blue });
        if ((round.reveled.red <= 12) and (round.reveled.green <= 13) and (round.reveled.blue <= 14)) {
            game_total += round.gameID;
        }
        game_min = game_min + (round.reveled.red * round.reveled.blue * round.reveled.green);
    }
    return .{ game_total, game_min };
}

pub fn solve() !void {
    const page_allocator = std.heap.page_allocator;
    if (helper.load_input_file(page_allocator, 2)) |f| {
        defer page_allocator.free(f);
        const ans = game_point(f);
        std.debug.print("Day 2 \n", .{});
        std.debug.print("- Part 1 : {d}\n", .{ans[0]});
        std.debug.print("- Part 2 : {d}\n", .{ans[1]});
    } else |err| {
        std.debug.print("\nsome error {s}", .{@errorName(err)});
    }
}

fn line_parser(line: []const u8) Round {
    var rnd: Round = Round{ .reveled = .{
        .green = 0,
        .blue = 0,
        .red = 0,
    } };
    var line_seg = std.mem.splitScalar(u8, line, ':');
    if (line_seg.next()) |v| {
        var game_segment = std.mem.splitScalar(u8, v, ' ');
        _ = game_segment.next();
        if (game_segment.next()) |w| {
            rnd.gameID = std.fmt.parseInt(i32, w, 10) catch 0;
            // std.debug.print("GAME {s}  :", .{w});
        }
    }
    if (line_seg.next()) |v| {
        var round_segmet = std.mem.splitScalar(u8, v, ';');
        while (round_segmet.next()) |round| {
            var round_summary = std.mem.splitScalar(u8, round, ',');
            while (round_summary.next()) |summary| {
                var cube_count: i32 = 0;
                var sum_split = std.mem.splitScalar(u8, summary, ' ');
                _ = sum_split.next();
                if (sum_split.next()) |val| {
                    cube_count = std.fmt.parseInt(i32, val, 10) catch 0;
                }
                if (sum_split.next()) |val| {
                    if (std.mem.eql(u8, val, "red")) {
                        rnd.reveled.red = if (rnd.reveled.red < cube_count) cube_count else rnd.reveled.red;
                    } else if (std.mem.eql(u8, val, "blue")) {
                        rnd.reveled.blue = if (rnd.reveled.blue < cube_count) cube_count else rnd.reveled.blue;
                    } else if (std.mem.eql(u8, val, "green")) {
                        rnd.reveled.green = if (rnd.reveled.green < cube_count) cube_count else rnd.reveled.green;
                    } else {}
                    // std.debug.print("\n SUM -==> {s} {?d}", .{ val, cube_count });
                }
            }
        }
    }
    // std.debug.print("\n", .{});
    return rnd;
}

test "Day 2" {
    const input =
        \\Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        \\Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
        \\Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
        \\Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
        \\Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green 
    ;
    const ans = game_point(input);
    try std.testing.expectEqual(8, ans[0]);
    try std.testing.expectEqual(2286, ans[1]);
}
