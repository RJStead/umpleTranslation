from MultipleSortedCourse import MultipleSortedCourse
from MultipleSortedStudent import MultipleSortedStudent
import os
import pickle

class MultipleSortedAcademy():
    def __init__(self, aRegistrantsPriority):
        self._registrantsPriority = aRegistrantsPriority
        self._multipleSortedCourses = []
        self._registrants = []

    def setRegistrantsPriority(self, aRegistrantsPriority):
        wasSet = False
        self._registrantsPriority = aRegistrantsPriority
        wasSet = True
        return wasSet

    def getRegistrantsPriority(self):
        return self._registrantsPriority

    def getMultipleSortedCourse(self, index):
        aMultipleSortedCourse = self._multipleSortedCourses[index]
        return aMultipleSortedCourse

    def getMultipleSortedCourses(self):
        newMultipleSortedCourses = tuple(self._multipleSortedCourses)
        return newMultipleSortedCourses

    def numberOfMultipleSortedCourses(self):
        number = len(self._multipleSortedCourses)
        return number

    def hasMultipleSortedCourses(self):
        has = len(self._multipleSortedCourses) > 0
        return has

    def indexOfMultipleSortedCourse(self, aMultipleSortedCourse):
        index = self._multipleSortedCourses.index(aMultipleSortedCourse)
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
    def minimumNumberOfMultipleSortedCourses():
        return 0

    def addMultipleSortedCourse(self, aCode, aMultipleSortedRegistrationsPriority):
        return MultipleSortedCourse(aCode, aMultipleSortedRegistrationsPriority, self)

    def addMultipleSortedCourse(self, aMultipleSortedCourse):
        wasAdded = False
        if aMultipleSortedCourse in self._multipleSortedCourses :
            return False
        existingMultipleSortedAcademy = aMultipleSortedCourse.getMultipleSortedAcademy()
        isNewMultipleSortedAcademy = existingMultipleSortedAcademy != None and not self.equals(existingMultipleSortedAcademy)
        if isNewMultipleSortedAcademy :
            aMultipleSortedCourse.setMultipleSortedAcademy(self)
        else :
            self._multipleSortedCourses.append(aMultipleSortedCourse)
        wasAdded = True
        return wasAdded

    def removeMultipleSortedCourse(self, aMultipleSortedCourse):
        wasRemoved = False
        if not self.equals(aMultipleSortedCourse.getMultipleSortedAcademy()) :
            self._multipleSortedCourses.remove(aMultipleSortedCourse)
            wasRemoved = True
        return wasRemoved

    def addMultipleSortedCourseAt(self, aMultipleSortedCourse, index):
        wasAdded = False
        if self.addMultipleSortedCourse(aMultipleSortedCourse) :
            if index < 0 :
                index = 0
            if index > self.numberOfMultipleSortedCourses() :
                index = self.numberOfMultipleSortedCourses() - 1
            self._multipleSortedCourses.remove(aMultipleSortedCourse)
            self._multipleSortedCourses.insert(index, aMultipleSortedCourse)
            wasAdded = True
        return wasAdded

    def addOrMoveMultipleSortedCourseAt(self, aMultipleSortedCourse, index):
        wasAdded = False
        if aMultipleSortedCourse in self._multipleSortedCourses :
            if index < 0 :
                index = 0
            if index > self.numberOfMultipleSortedCourses() :
                index = self.numberOfMultipleSortedCourses() - 1
            self._multipleSortedCourses.remove(aMultipleSortedCourse)
            self._multipleSortedCourses.insert(index, aMultipleSortedCourse)
            wasAdded = True
        else :
            wasAdded = self.addMultipleSortedCourseAt(aMultipleSortedCourse, index)
        return wasAdded

    @staticmethod
    def minimumNumberOfRegistrants():
        return 0

    def addRegistrant(self, aId, aName, aMultipleSortedRegistrationsPriority):
        return MultipleSortedStudent(aId, aName, aMultipleSortedRegistrationsPriority, self)

    def addRegistrant(self, aRegistrant):
        wasAdded = False
        if aRegistrant in self._registrants :
            return False
        existingMultipleSortedAcademy = aRegistrant.getMultipleSortedAcademy()
        isNewMultipleSortedAcademy = existingMultipleSortedAcademy != None and not self.equals(existingMultipleSortedAcademy)
        if isNewMultipleSortedAcademy :
            aRegistrant.setMultipleSortedAcademy(self)
        else :
            self._registrants.append(aRegistrant)
        wasAdded = True
        return wasAdded

    def removeRegistrant(self, aRegistrant):
        wasRemoved = False
        if not self.equals(aRegistrant.getMultipleSortedAcademy()) :
            self._registrants.remove(aRegistrant)
            wasRemoved = True
        return wasRemoved

    def readObject(self, input):
        self.__dict__.clear()
        self.__dict__.clear().update(pickle.load(input).__dict__)
        self._registrantsPriority = None

    def delete(self):
        i = len(self._multipleSortedCourses)
        while i > 0 :
            aMultipleSortedCourse = self._multipleSortedCourses[i - 1]
            aMultipleSortedCourse.delete()
            i = i - 1

        i = len(self._registrants)
        while i > 0 :
            aRegistrant = self._registrants[i - 1]
            aRegistrant.delete()
            i = i - 1

    def __str__(self):
        return str(super()) + "[" + "]" + os.linesep + "  " + "registrantsPriority" + "=" + (self.getRegistrantsPriority() != ((str(self.getRegistrantsPriority()).replaceAll("  ", "    ")) if not self.getRegistrantsPriority().equals(self) else "this") if None else "null")

    def __getstate__(self):
        state = dict(self.__dict__)
        state.pop("_registrantsPriority")
        return state

