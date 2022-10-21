from ProgramG import ProgramG
from MentorG import MentorG
import os

class StudentG():
    def __init__(self, aNumber, aMentor):
        self._number = aNumber
        if aMentor is None or aMentor.getStudent() != None :
            raise RuntimeError ("Unable to create StudentG due to aMentor. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html")
        self._mentor = aMentor
        self._program = None

    def __init__(self, aNumber, aNameForMentor):
        self._number = aNumber
        self._mentor = MentorG(aNameForMentor, self)
        self._program = None

    def setNumber(self, aNumber):
        wasSet = False
        self._number = aNumber
        wasSet = True
        return wasSet

    def getNumber(self):
        return self._number

    def getProgram(self):
        return self._program

    def hasProgram(self):
        has = not(self._program is None)
        return has

    def getMentor(self):
        return self._mentor

    def setProgram(self, aNewProgram):
        wasSet = False
        if aNewProgram is None :
            existingProgram = self._program
            self._program = None
            if existingProgram != None and existingProgram.getStudent() != None :
                existingProgram.setStudent(None)
            wasSet = True
            return wasSet
        currentProgram = self.getProgram()
        if currentProgram != None and not currentProgram.equals(aNewProgram) :
            currentProgram.setStudent(None)
        self._program = aNewProgram
        existingStudent = aNewProgram.getStudent()
        if not self.equals(existingStudent) :
            aNewProgram.setStudent(self)
        wasSet = True
        return wasSet

    def delete(self):
        if not(self._program is None) :
            self._program.setStudent(None)
        existingMentor = self._mentor
        self._mentor = None
        if not(existingMentor is None) :
            existingMentor.delete()

    def __str__(self):
        return str(super()) + "[" + "number" + ":" + self.getNumber() + "]" + os.linesep + "  " + "program = " + (self.getProgram() != (format(id(self.getProgram()), "x")) if None else "null") + os.linesep + "  " + "mentor = " + (self.getMentor() != (format(id(self.getMentor()), "x")) if None else "null")

