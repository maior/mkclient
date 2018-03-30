import pickle
import codecs
from contract import contract

contractkey = '0x6003284f23a3c3ef0234232dfa32'
filename = 'contract.dat'
candidateList = ['Rama', 'Ken', 'Rose']
module = contract()
module.appendCandidateNames(candidateList)
dumpmodule = pickle.dumps(module, protocol=pickle.HIGHEST_PROTOCOL)
encodemodule = codecs.encode(dumpmodule, 'hex_codec')
with open(filename, "a") as contractfile:
    contractfile.write(str(contractkey)+":"+str(encodemodule)[2:-1]+"\n")

print(encodemodule)
