from MentorAR import MentorAR

class StudentAR():
    def __init__(self):
        self._mentorAR = None

    def getMentorAR(self):
        return self._mentorAR

    def hasMentorAR(self):
        has = not(self._mentorAR is None)
        return has

    def setMentorAR(self, aMentorAR):
        wasSet = False
        existingMentorAR = self._mentorAR
        if existingMentorAR is None :
            if not(aMentorAR is None) :
                if aMentorAR.addStudentAR(self) :
                    existingMentorAR = aMentorAR
                    wasSet = True
        elif not(existingMentorAR is None) :
            if aMentorAR is None :
                if existingMentorAR.minimumNumberOfStudentARs() < existingMentorAR.numberOfStudentARs() :
                    existingMentorAR.removeStudentAR(self)
                    existingMentorAR = aMentorAR
                    wasSet = True
            else :
                if existingMentorAR.minimumNumberOfStudentARs() < existingMentorAR.numberOfStudentARs() :
                    existingMentorAR.removeStudentAR(self)
                    aMentorAR.addStudentAR(self)
                    existingMentorAR = aMentorAR
                    wasSet = True
        if wasSet :
            self._mentorAR = existingMentorAR
        return wasSet

    def delete(self):
        if not(self._mentorAR is None) :
            if self._mentorAR.numberOfStudentARs() < = 1 :
                self._mentorAR.delete()
            else :
                placeholderMentorAR = self._mentorAR
                self._mentorAR = None
                placeholderMentorAR.removeStudentAR(self)

