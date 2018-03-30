#!/usr/local/bin/python
class Test():

    ttime = "a"

    def __init__(self, name):
        self.name = name

    def tcall(self):
        print("name : " + self.name)
        print("ttime : " + self.ttime)

def main():
    print("Initializing mkblock")
    test = Test("test")
    test.tcall()
    #print("ttime : " + test.ttime)

if __name__ == "__main__":
    main()
