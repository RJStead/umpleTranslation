from MentorAM import MentorAM
import os

class StudentAM():
    def __init__(self, aId):
        self._id = aId
        self._mentor = None

    def setId(self, aId):
        wasSet = False
        self._id = aId
        wasSet = True
        return wasSet

    def getId(self):
        return self._id

    def getMentor(self):
        return self._mentor

    def hasMentor(self):
        has = not(self._mentor is None)
        return has

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
        if not(self._mentor is None) :
            placeholderMentor = self._mentor
            self._mentor = None
            placeholderMentor.removeStudent(self)

    def __str__(self):
        return str(super()) + "[" + "id" + ":" + self.getId() + "]" + os.linesep + "  " + "mentor = " + (self.getMentor() != (format(id(self.getMentor()), "x")) if None else "null")

