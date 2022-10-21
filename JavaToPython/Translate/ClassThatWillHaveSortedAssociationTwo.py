from ClassThatWillHaveSortedAssociationOne import ClassThatWillHaveSortedAssociationOne
import os
import pickle

class ClassThatWillHaveSortedAssociationTwo():
    def __init__(self, aMassPriority):
        self._massPriority = aMassPriority
        self._mass = []

    def setMassPriority(self, aMassPriority):
        wasSet = False
        self._massPriority = aMassPriority
        wasSet = True
        return wasSet

    def getMassPriority(self):
        return self._massPriority

    def getMass(self, index):
        aMass = self._mass[index]
        return aMass

    def getMass(self):
        newMass = tuple(self._mass)
        return newMass

    def numberOfMass(self):
        number = len(self._mass)
        return number

    def hasMass(self):
        has = len(self._mass) > 0
        return has

    def indexOfMass(self, aMass):
        index = self._mass.index(aMass)
        return index

    @staticmethod
    def minimumNumberOfMass():
        return 0

    def addMass(self, aMass):
        wasAdded = False
        if aMass in self._mass :
            return False
        self._mass.append(aMass)
        wasAdded = True
        return wasAdded

    def removeMass(self, aMass):
        wasRemoved = False
        if aMass in self._mass :
            self._mass.remove(aMass)
            wasRemoved = True
        return wasRemoved

    def readObject(self, input):
        self.__dict__.clear()
        self.__dict__.clear().update(pickle.load(input).__dict__)
        self._massPriority = None

    def delete(self):
        self._mass.clear()

    def __str__(self):
        return str(super()) + "[" + "]" + os.linesep + "  " + "massPriority" + "=" + (self.getMassPriority() != ((str(self.getMassPriority()).replaceAll("  ", "    ")) if not self.getMassPriority().equals(self) else "this") if None else "null")

    def __getstate__(self):
        state = dict(self.__dict__)
        state.pop("_massPriority")
        return state

