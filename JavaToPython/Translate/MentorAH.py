from StudentAH import StudentAH
from ProgramAH import ProgramAH
import os

class MentorAH():
    def __init__(self, aName, aStudent):
        self._name = aName
        if not self.setStudent(aStudent) :
            raise RuntimeError ("Unable to create MentorAH due to aStudent. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html")
        self._student = None
        self._program = None

    def setName(self, aName):
        wasSet = False
        self._name = aName
        wasSet = True
        return wasSet

    def getName(self):
        return self._name

    def getStudent(self):
        return self._student

    def getProgram(self):
        return self._program

    def hasProgram(self):
        has = not(self._program is None)
        return has

    def setStudent(self, aNewStudent):
        wasSet = False
        if not(aNewStudent is None) :
            self._student = aNewStudent
            wasSet = True
        return wasSet

    def setProgram(self, aNewProgram):
        wasSet = False
        if aNewProgram is None :
            existingProgram = self._program
            self._program = None
            if existingProgram != None and existingProgram.getMentor() != None :
                existingProgram.setMentor(None)
            wasSet = True
            return wasSet
        currentProgram = self.getProgram()
        if currentProgram != None and not currentProgram.equals(aNewProgram) :
            currentProgram.setMentor(None)
        self._program = aNewProgram
        existingMentor = aNewProgram.getMentor()
        if not self.equals(existingMentor) :
            aNewProgram.setMentor(self)
        wasSet = True
        return wasSet

    def delete(self):
        self._student = None
        if not(self._program is None) :
            self._program.setMentor(None)

    def __str__(self):
        return str(super()) + "[" + "name" + ":" + self.getName() + "]" + os.linesep + "  " + "student = " + (self.getStudent() != (format(id(self.getStudent()), "x")) if None else "null") + os.linesep + "  " + "program = " + (self.getProgram() != (format(id(self.getProgram()), "x")) if None else "null")

