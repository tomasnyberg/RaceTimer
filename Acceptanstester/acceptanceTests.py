import argparse
import os
import platform
import sys

ROOT_DIR = os.getcwd()
ACCEPTANCE_TESTS_DIR = os.path.join(ROOT_DIR, 'Acceptanstester')
EXECUTABLE = os.path.join(ROOT_DIR, 'result/build/libs/result-v0.1b.jar')

EXPECTED_RESULT_FILE = 'resultat.txt'
RESULT_FILE = 'resultat.txt'

def test_directory(dir):
    if not os.path.isdir(dir):
        print(f'directory: {dir} does not exist')
        sys.stdout.flush()
        return

    subdirectories = [os.path.join(dir, o) for o in os.listdir(dir) 
                      if os.path.isdir(os.path.join(dir, o))]
    if os.path.join(dir, 'input') not in subdirectories:
        for subdir in subdirectories:
            test_directory(os.path.join(dir, subdir))
        return

    cwd = os.getcwd()
    os.chdir(dir)

    output_file = os.path.join(dir, 'output', RESULT_FILE)
    if os.path.isfile(output_file):
        os.remove(output_file)

    print('=========================================')
    sys.stdout.flush()
    print(f'running test {dir}')
    sys.stdout.flush()
    
    os.system(f'java -jar {EXECUTABLE}')
    sys.stdout.flush()

    if os.path.isfile(output_file):
        if platform.system() == 'Windows':
            os.system(f'fc input\\{EXPECTED_RESULT_FILE} output\\{RESULT_FILE}')
            sys.stdout.flush()
        else:
            os.system(f'diff -y input/{EXPECTED_RESULT_FILE} output/{RESULT_FILE}')
            sys.stdout.flush()
    print()
    sys.stdout.flush()

    os.chdir(cwd)

def build_gradle():
    os.system('./gradlew test')
    sys.stdout.flush()

def main():
    parser = argparse.ArgumentParser()

    parser.add_argument('-b', '--build', dest='build', action='store_true',
                        help='run ./gradlew test')
    parser.add_argument('-nb', '--no-build', dest='build', action='store_false',
                        help='do not run ./gradlew test')
    parser.set_defaults(build=True)

    parser.add_argument('directories', type=str, nargs='*',
                        help='directories to test')

    args = parser.parse_args()

    if args.build:
        build_gradle()

    for dir in args.directories:
        test_directory(os.path.join(ACCEPTANCE_TESTS_DIR, dir))

if __name__ == "__main__":
    main()
