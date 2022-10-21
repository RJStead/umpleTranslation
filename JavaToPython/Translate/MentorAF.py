from MentorAF import MentorAF
import os

class MentorAF():
    def __init__(self, aName, aFriend):
        self._name = aName
        if aFriend is None or aFriend.getFriend() != None :
            raise RuntimeError ("Unable to create MentorAF due to aFriend. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html")
        self._friend = aFriend

    def __init__(self, aName, aNameForFriend):
        self._name = aName
        self._friend = MentorAF(aNameForFriend, self)

    def setName(self, aName):
        wasSet = False
        self._name = aName
        wasSet = True
        return wasSet

    def getName(self):
        return self._name

    def getFriend(self):
        return self._friend

    def delete(self):
        existingFriend = self._friend
        self._friend = None
        if not(existingFriend is None) :
            existingFriend.delete()

    def __str__(self):
        return str(super()) + "[" + "name" + ":" + self.getName() + "]" + os.linesep + "  " + "friend = " + (self.getFriend() != (format(id(self.getFriend()), "x")) if None else "null")

