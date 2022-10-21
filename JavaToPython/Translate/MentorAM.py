from StudentAM import StudentAM

class MentorAM():
    def __init__(self, aName):
        self._name = aName
        self._students = []

    def setName(self, aName):
        wasSet = False
        self._name = aName
        wasSet = True
        return wasSet

    def getName(self):
        return self._name

    def getStudent(self, index):
        aStudent = self._students[index]
        return aStudent

    def getStudents(self):
        newStudents = tuple(self._students)
        return newStudents

    def numberOfStudents(self):
        number = len(self._students)
        return number

    def hasStudents(self):
        has = len(self._students) > 0
        return has

    def indexOfStudent(self, aStudent):
        index = self._students.index(aStudent)
        return index

    @staticmethod
    def minimumNumberOfStudents():
        return 0

    def addStudent(self, aStudent):
        wasAdded = False
        if aStudent in self._students :
            return False
        existingMentor = aStudent.getMentor()
        if existingMentor is None :
            aStudent.setMentor(self)
        elif not self.equals(existingMentor) :
            existingMentor.removeStudent(aStudent)
            self.addStudent(aStudent)
        else :
            self._students.append(aStudent)
        wasAdded = True
        return wasAdded

    def removeStudent(self, aStudent):
        wasRemoved = False
        if aStudent in self._students :
            self._students.remove(aStudent)
            aStudent.setMentor(None)
            wasRemoved = True
        return wasRemoved

    def addStudentAt(self, aStudent, index):
        wasAdded = False
        if self.addStudent(aStudent) :
            if index < 0 :
                index = 0
            if index > self.numberOfStudents() :
                index = self.numberOfStudents() - 1
            self._students.remove(aStudent)
            self._students.insert(index, aStudent)
            wasAdded = True
        return wasAdded

    def addOrMoveStudentAt(self, aStudent, index):
        wasAdded = False
        if aStudent in self._students :
            if index < 0 :
                index = 0
            if index > self.numberOfStudents() :
                index = self.numberOfStudents() - 1
            self._students.remove(aStudent)
            self._students.insert(index, aStudent)
            wasAdded = True
        else :
            wasAdded = self.addStudentAt(aStudent, index)
        return wasAdded

    def delete(self):
        while not self._students.isEmpty() :
            self._students[0].setMentor(None)

    def __str__(self):
        return str(super()) + "[" + "name" + ":" + self.getName() + "]"

