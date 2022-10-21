from MentorE import MentorE
from StudentE import StudentE

class ProgramE():
    def __init__(self):
        self._mentor = None
        self._student = None

    def getMentor(self):
        return self._mentor

    def hasMentor(self):
        has = not(self._mentor is None)
        return has

    def getStudent(self):
        return self._student

    def hasStudent(self):
        has = not(self._student is None)
        return has

    def setMentor(self, aNewMentor):
        wasSet = False
        if aNewMentor is None :
            existingMentor = self._mentor
            self._mentor = None
            if existingMentor != None and existingMentor.getProgram() != None :
                existingMentor.setProgram(None)
            wasSet = True
            return wasSet
        currentMentor = self.getMentor()
        if currentMentor != None and not currentMentor.equals(aNewMentor) :
            currentMentor.setProgram(None)
        self._mentor = aNewMentor
        existingProgram = aNewMentor.getProgram()
        if not self.equals(existingProgram) :
            aNewMentor.setProgram(self)
        wasSet = True
        return wasSet

    def setStudent(self, aNewStudent):
        wasSet = False
        if aNewStudent is None :
            existingStudent = self._student
            self._student = None
            if existingStudent != None and existingStudent.getProgram() != None :
                existingStudent.setProgram(None)
            wasSet = True
            return wasSet
        currentStudent = self.getStudent()
        if currentStudent != None and not currentStudent.equals(aNewStudent) :
            currentStudent.setProgram(None)
        self._student = aNewStudent
        existingProgram = aNewStudent.getProgram()
        if not self.equals(existingProgram) :
            aNewStudent.setProgram(self)
        wasSet = True
        return wasSet

    def delete(self):
        if not(self._mentor is None) :
            self._mentor.setProgram(None)
        if not(self._student is None) :
            self._student.setProgram(None)

