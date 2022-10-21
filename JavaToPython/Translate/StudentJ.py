from ProgramJ import ProgramJ
from MentorJ import MentorJ
import os

class StudentJ():
    def __init__(self, aNumber, aMentor):
        self._number = aNumber
        didAddMentor = self.setMentor(aMentor)
        if not didAddMentor :
            raise RuntimeError ("Unable to create student due to mentor. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html")
        self._program = None
        self._mentor = None

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

    def setMentor(self, aMentor):
        wasSet = False
        if aMentor is None :
            return wasSet
        existingMentor = self._mentor
        self._mentor = aMentor
        if existingMentor != None and not existingMentor.equals(aMentor) :
            existingMentor.removeStudent(self)
        self._mentor.addStudent(self)
        wasSet = True
        return wasSet

    def delete(self):
        if not(self._program is None) :
            self._program.setStudent(None)
        placeholderMentor = self._mentor
        self._mentor = None
        if not(placeholderMentor is None) :
            placeholderMentor.removeStudent(self)

    def __str__(self):
        return str(super()) + "[" + "number" + ":" + self.getNumber() + "]" + os.linesep + "  " + "program = " + (self.getProgram() != (format(id(self.getProgram()), "x")) if None else "null") + os.linesep + "  " + "mentor = " + (self.getMentor() != (format(id(self.getMentor()), "x")) if None else "null")

