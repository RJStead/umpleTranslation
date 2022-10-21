from StudentC import StudentC
from StudentAL import StudentAL
import os
import pickle

class ClassWithManySortedAssociations():
    def __init__(self, aStudentCsPriority, aStudentALsPriority):
        self._studentCsPriority = aStudentCsPriority
        self._studentALsPriority = aStudentALsPriority
        self._studentCs = []
        self._studentALs = []

    def setStudentCsPriority(self, aStudentCsPriority):
        wasSet = False
        self._studentCsPriority = aStudentCsPriority
        wasSet = True
        return wasSet

    def setStudentALsPriority(self, aStudentALsPriority):
        wasSet = False
        self._studentALsPriority = aStudentALsPriority
        wasSet = True
        return wasSet

    def getStudentCsPriority(self):
        return self._studentCsPriority

    def getStudentALsPriority(self):
        return self._studentALsPriority

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

    def getStudentAL(self, index):
        aStudentAL = self._studentALs[index]
        return aStudentAL

    def getStudentALs(self):
        newStudentALs = tuple(self._studentALs)
        return newStudentALs

    def numberOfStudentALs(self):
        number = len(self._studentALs)
        return number

    def hasStudentALs(self):
        has = len(self._studentALs) > 0
        return has

    def indexOfStudentAL(self, aStudentAL):
        index = self._studentALs.index(aStudentAL)
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

    @staticmethod
    def minimumNumberOfStudentALs():
        return 0

    def addStudentAL(self, aStudentAL):
        wasAdded = False
        if aStudentAL in self._studentALs :
            return False
        self._studentALs.append(aStudentAL)
        wasAdded = True
        return wasAdded

    def removeStudentAL(self, aStudentAL):
        wasRemoved = False
        if aStudentAL in self._studentALs :
            self._studentALs.remove(aStudentAL)
            wasRemoved = True
        return wasRemoved

    def readObject(self, input):
        self.__dict__.clear()
        self.__dict__.clear().update(pickle.load(input).__dict__)
        self._studentCsPriority = None
        self._studentALsPriority = None

    def delete(self):
        self._studentCs.clear()
        self._studentALs.clear()

    def __str__(self):
        return str(super()) + "[" + "]" + os.linesep + "  " + "studentCsPriority" + "=" + (self.getStudentCsPriority() != ((str(self.getStudentCsPriority()).replaceAll("  ", "    ")) if not self.getStudentCsPriority().equals(self) else "this") if None else "null") + os.linesep + "  " + "studentALsPriority" + "=" + (self.getStudentALsPriority() != ((str(self.getStudentALsPriority()).replaceAll("  ", "    ")) if not self.getStudentALsPriority().equals(self) else "this") if None else "null")

    def __getstate__(self):
        state = dict(self.__dict__)
        state.pop("_studentCsPriority")
        state.pop("_studentALsPriority")
        return state

