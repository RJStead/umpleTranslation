import pickle

class ClassThatWillHaveSortedAssociationOne():
    def __init__(self, aName):
        self._name = aName

    def setName(self, aName):
        wasSet = False
        self._name = aName
        wasSet = True
        return wasSet

    def getName(self):
        return self._name

    def delete(self):
        pass

    def __str__(self):
        return str(super()) + "[" + "name" + ":" + self.getName() + "]"

