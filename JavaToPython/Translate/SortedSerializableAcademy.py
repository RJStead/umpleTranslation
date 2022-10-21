from SortedSerializableCourse import SortedSerializableCourse
from SortedSerializableStudent import SortedSerializableStudent
import os
import pickle

class SortedSerializableAcademy():
    def __init__(self, aSortedSerializableCoursesPriority, aRegistrantsPriority):
        self._sortedSerializableCoursesPriority = aSortedSerializableCoursesPriority
        self._registrantsPriority = aRegistrantsPriority
        self._sortedSerializableCourses = []
        self._registrants = []

    def setSortedSerializableCoursesPriority(self, aSortedSerializableCoursesPriority):
        wasSet = False
        self._sortedSerializableCoursesPriority = aSortedSerializableCoursesPriority
        wasSet = True
        return wasSet

    def setRegistrantsPriority(self, aRegistrantsPriority):
        wasSet = False
        self._registrantsPriority = aRegistrantsPriority
        wasSet = True
        return wasSet

    def getSortedSerializableCoursesPriority(self):
        return self._sortedSerializableCoursesPriority

    def getRegistrantsPriority(self):
        return self._registrantsPriority

    def getSortedSerializableCourse(self, index):
        aSortedSerializableCourse = self._sortedSerializableCourses[index]
        return aSortedSerializableCourse

    def getSortedSerializableCourses(self):
        newSortedSerializableCourses = tuple(self._sortedSerializableCourses)
        return newSortedSerializableCourses

    def numberOfSortedSerializableCourses(self):
        number = len(self._sortedSerializableCourses)
        return number

    def hasSortedSerializableCourses(self):
        has = len(self._sortedSerializableCourses) > 0
        return has

    def indexOfSortedSerializableCourse(self, aSortedSerializableCourse):
        index = self._sortedSerializableCourses.index(aSortedSerializableCourse)
        return index

    def getRegistrant(self, index):
        aRegistrant = self._registrants[index]
        return aRegistrant

    def getRegistrants(self):
        newRegistrants = tuple(self._registrants)
        return newRegistrants

    def numberOfRegistrants(self):
        number = len(self._registrants)
        return number

    def hasRegistrants(self):
        has = len(self._registrants) > 0
        return has

    def indexOfRegistrant(self, aRegistrant):
        index = self._registrants.index(aRegistrant)
        return index

    @staticmethod
    def minimumNumberOfSortedSerializableCourses():
        return 0

    def addSortedSerializableCourse(self, aCode, aSortedSerializableRegistrationsPriority):
        return SortedSerializableCourse(aCode, aSortedSerializableRegistrationsPriority, self)

    def addSortedSerializableCourse(self, aSortedSerializableCourse):
        wasAdded = False
        if aSortedSerializableCourse in self._sortedSerializableCourses :
            return False
        existingSortedSerializableAcademy = aSortedSerializableCourse.getSortedSerializableAcademy()
        isNewSortedSerializableAcademy = existingSortedSerializableAcademy != None and not self.equals(existingSortedSerializableAcademy)
        if isNewSortedSerializableAcademy :
            aSortedSerializableCourse.setSortedSerializableAcademy(self)
        else :
            self._sortedSerializableCourses.append(aSortedSerializableCourse)
        wasAdded = True
        return wasAdded

    def removeSortedSerializableCourse(self, aSortedSerializableCourse):
        wasRemoved = False
        if not self.equals(aSortedSerializableCourse.getSortedSerializableAcademy()) :
            self._sortedSerializableCourses.remove(aSortedSerializableCourse)
            wasRemoved = True
        return wasRemoved

    @staticmethod
    def minimumNumberOfRegistrants():
        return 0

    def addRegistrant(self, aId, aName):
        return SortedSerializableStudent(aId, aName, self)

    def addRegistrant(self, aRegistrant):
        wasAdded = False
        if aRegistrant in self._registrants :
            return False
        existingSortedSerializableAcademy = aRegistrant.getSortedSerializableAcademy()
        isNewSortedSerializableAcademy = existingSortedSerializableAcademy != None and not self.equals(existingSortedSerializableAcademy)
        if isNewSortedSerializableAcademy :
            aRegistrant.setSortedSerializableAcademy(self)
        else :
            self._registrants.append(aRegistrant)
        wasAdded = True
        return wasAdded

    def removeRegistrant(self, aRegistrant):
        wasRemoved = False
        if not self.equals(aRegistrant.getSortedSerializableAcademy()) :
            self._registrants.remove(aRegistrant)
            wasRemoved = True
        return wasRemoved

    def readObject(self, input):
        self.__dict__.clear()
        self.__dict__.clear().update(pickle.load(input).__dict__)
        self._sortedSerializableCoursesPriority = None
        self._registrantsPriority = None

    def delete(self):
        i = len(self._sortedSerializableCourses)
        while i > 0 :
            aSortedSerializableCourse = self._sortedSerializableCourses[i - 1]
            aSortedSerializableCourse.delete()
            i = i - 1

        i = len(self._registrants)
        while i > 0 :
            aRegistrant = self._registrants[i - 1]
            aRegistrant.delete()
            i = i - 1

    def __str__(self):
        return str(super()) + "[" + "]" + os.linesep + "  " + "sortedSerializableCoursesPriority" + "=" + (self.getSortedSerializableCoursesPriority() != ((str(self.getSortedSerializableCoursesPriority()).replaceAll("  ", "    ")) if not self.getSortedSerializableCoursesPriority().equals(self) else "this") if None else "null") + os.linesep + "  " + "registrantsPriority" + "=" + (self.getRegistrantsPriority() != ((str(self.getRegistrantsPriority()).replaceAll("  ", "    ")) if not self.getRegistrantsPriority().equals(self) else "this") if None else "null")

    def __getstate__(self):
        state = dict(self.__dict__)
        state.pop("_sortedSerializableCoursesPriority")
        state.pop("_registrantsPriority")
        return state

