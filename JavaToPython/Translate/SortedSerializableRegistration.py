from SortedSerializableStudent import SortedSerializableStudent
from SortedSerializableCourse import SortedSerializableCourse
import os
import pickle

class SortedSerializableRegistration():
    def __init__(self, aSortedSerializableStudent, aSortedSerializableCourse):
        didAddSortedSerializableStudent = self.setSortedSerializableStudent(aSortedSerializableStudent)
        if not didAddSortedSerializableStudent :
            raise RuntimeError ("Unable to create sortedSerializableRegistration due to sortedSerializableStudent. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html")
        didAddSortedSerializableCourse = self.setSortedSerializableCourse(aSortedSerializableCourse)
        if not didAddSortedSerializableCourse :
            raise RuntimeError ("Unable to create sortedSerializableRegistration due to sortedSerializableCourse. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html")
        self._sortedSerializableStudent = None
        self._sortedSerializableCourse = None

    def getName(self):
        return self.getSortedSerializableStudent().getName()

    def getCode(self):
        return self.getSortedSerializableCourse().getCode()

    def getSortedSerializableStudent(self):
        return self._sortedSerializableStudent

    def getSortedSerializableCourse(self):
        return self._sortedSerializableCourse

    def setSortedSerializableStudent(self, aSortedSerializableStudent):
        wasSet = False
        if aSortedSerializableStudent is None :
            return wasSet
        existingSortedSerializableStudent = self._sortedSerializableStudent
        self._sortedSerializableStudent = aSortedSerializableStudent
        if existingSortedSerializableStudent != None and not existingSortedSerializableStudent.equals(aSortedSerializableStudent) :
            existingSortedSerializableStudent.removeSortedSerializableRegistration(self)
        self._sortedSerializableStudent.addSortedSerializableRegistration(self)
        wasSet = True
        return wasSet

    def setSortedSerializableCourse(self, aSortedSerializableCourse):
        wasSet = False
        if aSortedSerializableCourse is None :
            return wasSet
        existingSortedSerializableCourse = self._sortedSerializableCourse
        self._sortedSerializableCourse = aSortedSerializableCourse
        if existingSortedSerializableCourse != None and not existingSortedSerializableCourse.equals(aSortedSerializableCourse) :
            existingSortedSerializableCourse.removeSortedSerializableRegistration(self)
        self._sortedSerializableCourse.addSortedSerializableRegistration(self)
        wasSet = True
        return wasSet

    def delete(self):
        placeholderSortedSerializableStudent = self._sortedSerializableStudent
        self._sortedSerializableStudent = None
        if not(placeholderSortedSerializableStudent is None) :
            placeholderSortedSerializableStudent.removeSortedSerializableRegistration(self)
        placeholderSortedSerializableCourse = self._sortedSerializableCourse
        self._sortedSerializableCourse = None
        if not(placeholderSortedSerializableCourse is None) :
            placeholderSortedSerializableCourse.removeSortedSerializableRegistration(self)

    def __str__(self):
        return str(super()) + "[" + "name" + ":" + self.getName() + "," + "code" + ":" + self.getCode() + "]" + os.linesep + "  " + "sortedSerializableStudent = " + (self.getSortedSerializableStudent() != (format(id(self.getSortedSerializableStudent()), "x")) if None else "null") + os.linesep + "  " + "sortedSerializableCourse = " + (self.getSortedSerializableCourse() != (format(id(self.getSortedSerializableCourse()), "x")) if None else "null")

