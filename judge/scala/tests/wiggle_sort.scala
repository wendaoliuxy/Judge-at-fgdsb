package test
import scala.io.Source
import judge.common
import judge.Solution

object wiggle_sort {
    val num_test = 100;
    var in_0 = List[List[Int]]();
    var out = List[List[Int]]();

    def test_wiggle(arr: List[Int], len: Int): Boolean = {
			def solve(as: List[Int], flag: Boolean): Boolean = as match {
				case h1::h2::t =>
					if((flag && h1 > h2) || (!flag && h1 < h2)) false
					else solve(h2::t, !flag)
				case _ => true
			}
			if (arr.length != len) false
			else solve(arr, true)
		}

    def load_test() = {
        val in = Source.fromFile("./judge/tests/wiggle-sort.txt").getLines;
        in_0 = common.read_int_matrix(in);
        out = common.read_int_matrix(in);
    }

    def judge(): Int = {
        load_test();
        common.capture_stdout();

        val startTime = System.currentTimeMillis();
        var i = 0;

        while(i < num_test) {
            printf("Testing case #%d\n", i+1);
            val answer = Solution.wiggle_sort(in_0(i));
            if(!test_wiggle(answer, out(i).length)) {
                common.release_stdout();
                printf("%d / %d;", i+1, num_test);
                var outs = common.to_string(wiggle_sort.in_0(i));
                print(outs + ";");
                print(common.to_string(answer) + ";");
                println(common.to_string(out(i)));
                return 1;
            }
            i += 1;
        }

        common.release_stdout();
        val estimatedTime = System.currentTimeMillis - startTime;
        print("Accepted;");
        println(estimatedTime);
        return 0;
    }
}
