import os
from contract import contract

class Voting(contract):
     
     candidate = ["Rama", "Rose", "Ken"]
     totalcount = 10

    def __init__(self, address, name):
        contract.__init__(self, address, name)
        print("name : " + name)

        fp = open("dump.cb", "w")
        for item in self.candidate:
            fp.write("%s\n" % item)
 
        print("COMPLETED!!!!!!")           # DO NOT DELETE or CHANGE!!!!!!

    def totalVotesFor(self):
        print("totalcount : " + self.totalcount++)

    def voteForCandidate(name, votesInTokens):
        return 0

