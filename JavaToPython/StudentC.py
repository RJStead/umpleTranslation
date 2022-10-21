from ProgramC import ProgramC
from MentorC import MentorC
import os
import pickle

class StudentC():
    def __init__(self, aId):
        self._id = aId
        self._program = None
        self._mentor = None

    def setId(self, aId):
        wasSet = False
        self._id = aId
        wasSet = True
        return wasSet

    def getId(self):
        return self._id

    def getProgram(self):
        return self._program

    def hasProgram(self):
        has = not(self._program is None)
        return has

    def getMentor(self):
        return self._mentor

    def hasMentor(self):
        has = not(self._mentor is None)
        return has

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
        existingMentor = self._mentor
        self._mentor = aMentor
        if existingMentor != None and not existingMentor.equals(aMentor) :
            existingMentor.removeStudent(self)
        if not(aMentor is None) :
            aMentor.addStudent(self)
        wasSet = True
        return wasSet

    def delete(self):
        if not(self._program is None) :
            self._program.setStudent(None)
        if not(self._mentor is None) :
            placeholderMentor = self._mentor
            self._mentor = None
            placeholderMentor.removeStudent(self)

    def __str__(self):
        return str(super()) + "[" + "id" + ":" + self.getId() + "]" + os.linesep + "  " + "program = " + (self.getProgram() != (format(id(self.getProgram()), "x")) if None else "null") + os.linesep + "  " + "mentor = " + (self.getMentor() != (format(id(self.getMentor()), "x")) if None else "null")

