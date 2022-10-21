from MentorI import MentorI
from StudentI import StudentI

class ProgramI():
    def __init__(self):
        self._mentorI = None
        self._studentI = None

    def getMentorI(self):
        return self._mentorI

    def hasMentorI(self):
        has = not(self._mentorI is None)
        return has

    def getStudentI(self):
        return self._studentI

    def hasStudentI(self):
        has = not(self._studentI is None)
        return has

    def setMentorI(self, aNewMentorI):
        wasSet = False
        if aNewMentorI is None :
            existingMentorI = self._mentorI
            self._mentorI = None
            if existingMentorI != None and existingMentorI.getProgram() != None :
                existingMentorI.setProgram(None)
            wasSet = True
            return wasSet
        currentMentorI = self.getMentorI()
        if currentMentorI != None and not currentMentorI.equals(aNewMentorI) :
            currentMentorI.setProgram(None)
        self._mentorI = aNewMentorI
        existingProgram = aNewMentorI.getProgram()
        if not self.equals(existingProgram) :
            aNewMentorI.setProgram(self)
        wasSet = True
        return wasSet

    def setStudentI(self, aNewStudentI):
        wasSet = False
        if aNewStudentI is None :
            existingStudentI = self._studentI
            self._studentI = None
            if existingStudentI != None and existingStudentI.getProgram() != None :
                existingStudentI.setProgram(None)
            wasSet = True
            return wasSet
        currentStudentI = self.getStudentI()
        if currentStudentI != None and not currentStudentI.equals(aNewStudentI) :
            currentStudentI.setProgram(None)
        self._studentI = aNewStudentI
        existingProgram = aNewStudentI.getProgram()
        if not self.equals(existingProgram) :
            aNewStudentI.setProgram(self)
        wasSet = True
        return wasSet

    def delete(self):
        if not(self._mentorI is None) :
            self._mentorI.setProgram(None)
        if not(self._studentI is None) :
            self._studentI.setProgram(None)

