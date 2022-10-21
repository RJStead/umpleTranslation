from MentorH import MentorH
import os

class MentorH():
    def __init__(self, aName):
        self._name = aName
        self._superMentor = None

    def setName(self, aName):
        wasSet = False
        self._name = aName
        wasSet = True
        return wasSet

    def getName(self):
        return self._name

    def getSuperMentor(self):
        return self._superMentor

    def hasSuperMentor(self):
        has = not(self._superMentor is None)
        return has

    def setSuperMentor(self, aNewSuperMentor):
        wasSet = False
        if aNewSuperMentor is None :
            existingSuperMentor = self._superMentor
            self._superMentor = None
            if existingSuperMentor != None and existingSuperMentor.getSuperMentor() != None :
                existingSuperMentor.setSuperMentor(None)
            wasSet = True
            return wasSet
        currentSuperMentor = self.getSuperMentor()
        if currentSuperMentor != None and not currentSuperMentor.equals(aNewSuperMentor) :
            currentSuperMentor.setSuperMentor(None)
        self._superMentor = aNewSuperMentor
        existingSuperMentor = aNewSuperMentor.getSuperMentor()
        if not self.equals(existingSuperMentor) :
            aNewSuperMentor.setSuperMentor(self)
        wasSet = True
        return wasSet

    def delete(self):
        if not(self._superMentor is None) :
            self._superMentor.setSuperMentor(None)

    def __str__(self):
        return str(super()) + "[" + "name" + ":" + self.getName() + "]" + os.linesep + "  " + "superMentor = " + (self.getSuperMentor() != (format(id(self.getSuperMentor()), "x")) if None else "null")

