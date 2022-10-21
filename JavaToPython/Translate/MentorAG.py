from MentorAG import MentorAG

class MentorAG():
    def __init__(self, aName):
        self._name = aName
        self._friend = []

    def setName(self, aName):
        wasSet = False
        self._name = aName
        wasSet = True
        return wasSet

    def getName(self):
        return self._name

    def getFriend(self, index):
        aFriend = self._friend[index]
        return aFriend

    def getFriend(self):
        newFriend = tuple(self._friend)
        return newFriend

    def numberOfFriend(self):
        number = len(self._friend)
        return number

    def hasFriend(self):
        has = len(self._friend) > 0
        return has

    def indexOfFriend(self, aFriend):
        index = self._friend.index(aFriend)
        return index

    @staticmethod
    def minimumNumberOfFriend():
        return 0

    def addFriend(self, aFriend):
        wasAdded = False
        if aFriend in self._friend :
            return False
        self._friend.append(aFriend)
        if aFriend.indexOfFriend(self) != -1 :
            wasAdded = True
        else :
            wasAdded = aFriend.addFriend(self)
            if not wasAdded :
                self._friend.remove(aFriend)
        return wasAdded

    def removeFriend(self, aFriend):
        wasRemoved = False
        if not self._friend.contains(aFriend) :
            return wasRemoved
        oldIndex = self._friend.index(aFriend)
        self._friend.remove(oldIndex)
        if aFriend.indexOfFriend(self) == -1 :
            wasRemoved = True
        else :
            wasRemoved = aFriend.removeFriend(self)
            if not wasRemoved :
                self._friend.insert(oldIndex, aFriend)
        return wasRemoved

    def addFriendAt(self, aFriend, index):
        wasAdded = False
        if self.addFriend(aFriend) :
            if index < 0 :
                index = 0
            if index > self.numberOfFriend() :
                index = self.numberOfFriend() - 1
            self._friend.remove(aFriend)
            self._friend.insert(index, aFriend)
            wasAdded = True
        return wasAdded

    def addOrMoveFriendAt(self, aFriend, index):
        wasAdded = False
        if aFriend in self._friend :
            if index < 0 :
                index = 0
            if index > self.numberOfFriend() :
                index = self.numberOfFriend() - 1
            self._friend.remove(aFriend)
            self._friend.insert(index, aFriend)
            wasAdded = True
        else :
            wasAdded = self.addFriendAt(aFriend, index)
        return wasAdded

    def delete(self):
        copyOfFriend = self._friend.copy()
        self._friend.clear()
        for aFriend in copyOfFriend:
            aFriend.removeFriend(self)

    def __str__(self):
        return str(super()) + "[" + "name" + ":" + self.getName() + "]"

