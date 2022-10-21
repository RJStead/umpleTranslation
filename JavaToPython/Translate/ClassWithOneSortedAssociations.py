from StudentC import StudentC
import os
import pickle

class ClassWithOneSortedAssociations():
    def __init__(self, aStudentCsPriority):
        self._studentCsPriority = aStudentCsPriority
        self._studentCs = []

    def setStudentCsPriority(self, aStudentCsPriority):
        wasSet = False
        self._studentCsPriority = aStudentCsPriority
        wasSet = True
        return wasSet

    def getStudentCsPriority(self):
        return self._studentCsPriority

    def getStudentC(self, index):
        aStudentC = self._studentCs[index]
        return aStudentC

    def getStudentCs(self):
        newStudentCs = tuple(self._studentCs)
        return newStudentCs

    def numberOfStudentCs(self):
        number = len(self._studentCs)
        return number

    def hasStudentCs(self):
        has = len(self._studentCs) > 0
        return has

    def indexOfStudentC(self, aStudentC):
        index = self._studentCs.index(aStudentC)
        return index

    @staticmethod
    def minimumNumberOfStudentCs():
        return 0

    def addStudentC(self, aStudentC):
        wasAdded = False
        if aStudentC in self._studentCs :
            return False
        self._studentCs.append(aStudentC)
        wasAdded = True
        return wasAdded

    def removeStudentC(self, aStudentC):
        wasRemoved = False
        if aStudentC in self._studentCs :
            self._studentCs.remove(aStudentC)
            wasRemoved = True
        return wasRemoved

    def readObject(self, input):
        self.__dict__.clear()
        self.__dict__.clear().update(pickle.load(input).__dict__)
        self._studentCsPriority = None

    def delete(self):
        self._studentCs.clear()

    def __str__(self):
        return str(super()) + "[" + "]" + os.linesep + "  " + "studentCsPriority" + "=" + (self.getStudentCsPriority() != ((str(self.getStudentCsPriority()).replaceAll("  ", "    ")) if not self.getStudentCsPriority().equals(self) else "this") if None else "null")

    def __getstate__(self):
        state = dict(self.__dict__)
        state.pop("_studentCsPriority")
        return state

