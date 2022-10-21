from MentorA import MentorA

class StudentA():
    def __init__(self):
        self._mentor = None

    def getMentor(self):
        return self._mentor

    def hasMentor(self):
        has = not(self._mentor is None)
        return has

    def setMentor(self, aNewMentor):
        wasSet = False
        if aNewMentor is None :
            existingMentor = self._mentor
            self._mentor = None
            if existingMentor != None and existingMentor.getStudent() != None :
                existingMentor.setStudent(None)
            wasSet = True
            return wasSet
        currentMentor = self.getMentor()
        if currentMentor != None and not currentMentor.equals(aNewMentor) :
            currentMentor.setStudent(None)
        self._mentor = aNewMentor
        existingStudent = aNewMentor.getStudent()
        if not self.equals(existingStudent) :
            aNewMentor.setStudent(self)
        wasSet = True
        return wasSet

    def delete(self):
        if not(self._mentor is None) :
            self._mentor.setStudent(None)

