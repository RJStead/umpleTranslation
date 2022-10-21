from MultipleSortedCourse import MultipleSortedCourse
from MultipleSortedStudent import MultipleSortedStudent
import os
import pickle

class MultipleSortedRegistration():
    def __init__(self, aMultipleSortedCourse, aMultipleSortedStudent):
        self._multipleSortedCourse = aMultipleSortedCourse
        self._multipleSortedStudent = aMultipleSortedStudent
        didAddMultipleSortedCourse = self.setMultipleSortedCourse(aMultipleSortedCourse)
        if not didAddMultipleSortedCourse :
            raise RuntimeError ("Unable to create multipleSortedRegistration due to multipleSortedCourse. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html")
        didAddMultipleSortedStudent = self.setMultipleSortedStudent(aMultipleSortedStudent)
        if not didAddMultipleSortedStudent :
            raise RuntimeError ("Unable to create multipleSortedRegistration due to multipleSortedStudent. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html")

    def getName(self):
        return self.getMultipleSortedStudent().getName()

    def getCode(self):
        return self.getMultipleSortedCourse().getCode()

    def getMultipleSortedCourse(self):
        return self._multipleSortedCourse

    def getMultipleSortedStudent(self):
        return self._multipleSortedStudent

    def setMultipleSortedCourse(self, aMultipleSortedCourse):
        wasSet = False
        if aMultipleSortedCourse is None :
            return wasSet
        existingMultipleSortedCourse = self._multipleSortedCourse
        self._multipleSortedCourse = aMultipleSortedCourse
        if existingMultipleSortedCourse != None and not existingMultipleSortedCourse.equals(aMultipleSortedCourse) :
            existingMultipleSortedCourse.removeMultipleSortedRegistration(self)
        self._multipleSortedCourse.addMultipleSortedRegistration(self)
        wasSet = True
        return wasSet

    def setMultipleSortedStudent(self, aMultipleSortedStudent):
        wasSet = False
        if aMultipleSortedStudent is None :
            return wasSet
        existingMultipleSortedStudent = self._multipleSortedStudent
        self._multipleSortedStudent = aMultipleSortedStudent
        if existingMultipleSortedStudent != None and not existingMultipleSortedStudent.equals(aMultipleSortedStudent) :
            existingMultipleSortedStudent.removeMultipleSortedRegistration(self)
        self._multipleSortedStudent.addMultipleSortedRegistration(self)
        wasSet = True
        return wasSet

    def delete(self):
        placeholderMultipleSortedCourse = self._multipleSortedCourse
        self._multipleSortedCourse = None
        if not(placeholderMultipleSortedCourse is None) :
            placeholderMultipleSortedCourse.removeMultipleSortedRegistration(self)
        placeholderMultipleSortedStudent = self._multipleSortedStudent
        self._multipleSortedStudent = None
        if not(placeholderMultipleSortedStudent is None) :
            placeholderMultipleSortedStudent.removeMultipleSortedRegistration(self)

    def __str__(self):
        return str(super()) + "[" + "name" + ":" + self.getName() + "," + "code" + ":" + self.getCode() + "]" + os.linesep + "  " + "multipleSortedCourse = " + (self.getMultipleSortedCourse() != (format(id(self.getMultipleSortedCourse()), "x")) if None else "null") + os.linesep + "  " + "multipleSortedStudent = " + (self.getMultipleSortedStudent() != (format(id(self.getMultipleSortedStudent()), "x")) if None else "null")

