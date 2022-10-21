from StudentA import StudentA

class MentorA():
    def __init__(self):
        self._student = None

    def getStudent(self):
        return self._student

    def hasStudent(self):
        has = not(self._student is None)
        return has

    def setStudent(self, aNewStudent):
        wasSet = False
        if aNewStudent is None :
            existingStudent = self._student
            self._student = None
            if existingStudent != None and existingStudent.getMentor() != None :
                existingStudent.setMentor(None)
            wasSet = True
            return wasSet
        currentStudent = self.getStudent()
        if currentStudent != None and not currentStudent.equals(aNewStudent) :
            currentStudent.setMentor(None)
        self._student = aNewStudent
        existingMentor = aNewStudent.getMentor()
        if not self.equals(existingMentor) :
            aNewStudent.setMentor(self)
        wasSet = True
        return wasSet

    def delete(self):
        if not(self._student is None) :
            self._student.setMentor(None)

