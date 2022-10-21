from Mentor import Mentor
import os
import pickle

class Student():
    def __init__(self, aId):
        self._id = aId
        self._mentorsPriority = lambda x : x.getName()
        self._mentors = []

    def setId(self, aId):
        wasSet = False
        self._id = aId
        wasSet = True
        return wasSet

    def setMentorsPriority(self, aMentorsPriority):
        wasSet = False
        self._mentorsPriority = aMentorsPriority
        wasSet = True
        return wasSet

    def getId(self):
        return self._id

    def getMentorsPriority(self):
        return self._mentorsPriority

    def getMentor(self, index):
        aMentor = self._mentors[index]
        return aMentor

    def getMentors(self):
        newMentors = tuple(self._mentors)
        return newMentors

    def numberOfMentors(self):
        number = len(self._mentors)
        return number

    def hasMentors(self):
        has = len(self._mentors) > 0
        return has

    def indexOfMentor(self, aMentor):
        index = self._mentors.index(aMentor)
        return index

    @staticmethod
    def minimumNumberOfMentors():
        return 0

    def addMentor(self, aMentor):
        wasAdded = False
        if aMentor in self._mentors :
            return False
        self._mentors.append(aMentor)
        if aMentor.indexOfStudent(self) != -1 :
            wasAdded = True
        else :
            wasAdded = aMentor.addStudent(self)
            if not wasAdded :
                self._mentors.remove(aMentor)
        if wasAdded :
            self._mentors.sort(key = self._mentorsPriority)
        return wasAdded

    def removeMentor(self, aMentor):
        wasRemoved = False
        if not self._mentors.contains(aMentor) :
            return wasRemoved
        oldIndex = self._mentors.index(aMentor)
        self._mentors.remove(oldIndex)
        if aMentor.indexOfStudent(self) == -1 :
            wasRemoved = True
        else :
            wasRemoved = aMentor.removeStudent(self)
            if not wasRemoved :
                self._mentors.insert(oldIndex, aMentor)
        return wasRemoved

    def readObject(self, input):
        self.__dict__.clear()
        self.__dict__.clear().update(pickle.load(input).__dict__)
        self._mentorsPriority = lambda x : x.getName()

    def delete(self):
        copyOfMentors = self._mentors.copy()
        self._mentors.clear()
        for aMentor in copyOfMentors:
            aMentor.removeStudent(self)

    def __str__(self):
        return str(super()) + "[" + "id" + ":" + self.getId() + "]" + os.linesep + "  " + "mentorsPriority" + "=" + (self.getMentorsPriority() != ((str(self.getMentorsPriority()).replaceAll("  ", "    ")) if not self.getMentorsPriority().equals(self) else "this") if None else "null")

    def __getstate__(self):
        state = dict(self.__dict__)
        state.pop("_mentorsPriority")
        return state

