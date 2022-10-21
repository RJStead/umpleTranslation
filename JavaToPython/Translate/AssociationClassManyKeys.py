from AssociatedClassWithKey import AssociatedClassWithKey

class AssociationClassManyKeys():
    def __init__(self):
        self._cachedHashCode = -1
        self._canSetAssociatedClasses = True
        self._associatedClasses = []

    def getAssociatedClass(self, index):
        aAssociatedClass = self._associatedClasses[index]
        return aAssociatedClass

    def getAssociatedClasses(self):
        newAssociatedClasses = tuple(self._associatedClasses)
        return newAssociatedClasses

    def numberOfAssociatedClasses(self):
        number = len(self._associatedClasses)
        return number

    def hasAssociatedClasses(self):
        has = len(self._associatedClasses) > 0
        return has

    def indexOfAssociatedClass(self, aAssociatedClass):
        index = self._associatedClasses.index(aAssociatedClass)
        return index

    @staticmethod
    def minimumNumberOfAssociatedClasses():
        return 0

    def addAssociatedClass(self, aAssociatedClass):
        wasAdded = False
        if not self._canSetAssociatedClasses :
            return False
        if aAssociatedClass in self._associatedClasses :
            return False
        self._associatedClasses.append(aAssociatedClass)
        if aAssociatedClass.indexOfAssociationClassManyKey(self) != -1 :
            wasAdded = True
        else :
            wasAdded = aAssociatedClass.addAssociationClassManyKey(self)
            if not wasAdded :
                self._associatedClasses.remove(aAssociatedClass)
        return wasAdded

    def removeAssociatedClass(self, aAssociatedClass):
        wasRemoved = False
        if not self._canSetAssociatedClasses :
            return False
        if not self._associatedClasses.contains(aAssociatedClass) :
            return wasRemoved
        oldIndex = self._associatedClasses.index(aAssociatedClass)
        self._associatedClasses.remove(oldIndex)
        if aAssociatedClass.indexOfAssociationClassManyKey(self) == -1 :
            wasRemoved = True
        else :
            wasRemoved = aAssociatedClass.removeAssociationClassManyKey(self)
            if not wasRemoved :
                self._associatedClasses.insert(oldIndex, aAssociatedClass)
        return wasRemoved

    def addAssociatedClassAt(self, aAssociatedClass, index):
        wasAdded = False
        if self.addAssociatedClass(aAssociatedClass) :
            if index < 0 :
                index = 0
            if index > self.numberOfAssociatedClasses() :
                index = self.numberOfAssociatedClasses() - 1
            self._associatedClasses.remove(aAssociatedClass)
            self._associatedClasses.insert(index, aAssociatedClass)
            wasAdded = True
        return wasAdded

    def addOrMoveAssociatedClassAt(self, aAssociatedClass, index):
        wasAdded = False
        if aAssociatedClass in self._associatedClasses :
            if index < 0 :
                index = 0
            if index > self.numberOfAssociatedClasses() :
                index = self.numberOfAssociatedClasses() - 1
            self._associatedClasses.remove(aAssociatedClass)
            self._associatedClasses.insert(index, aAssociatedClass)
            wasAdded = True
        else :
            wasAdded = self.addAssociatedClassAt(aAssociatedClass, index)
        return wasAdded

    def equals(self, obj):
        if obj is None :
            return False
        if not self.getClass().equals(obj.getClass()) :
            return False
        compareTo = obj
        if self.getAssociatedClasses().size() != compareTo.getAssociatedClasses().size() :
            return False
        i = 0
        while i < self.getAssociatedClasses().size() :
            me = self.getAssociatedClasses().get(i)
            them = compareTo.getAssociatedClasses().get(i)
            if me is None and them != None :
                return False
            elif me != None and not me.equals(them) :
                return False
            i = i + 1

        return True

    def hashCode(self):
        if self._cachedHashCode != -1 :
            return self._cachedHashCode
        self._cachedHashCode = 17
        if not(self.getAssociatedClasses() is None) :
            self._cachedHashCode = self._cachedHashCode * 23 + self.getAssociatedClasses().hashCode()
        else :
            self._cachedHashCode = self._cachedHashCode * 23
        self._canSetAssociatedClasses = False
        return self._cachedHashCode

    def delete(self):
        copyOfAssociatedClasses = self._associatedClasses.copy()
        self._associatedClasses.clear()
        for aAssociatedClass in copyOfAssociatedClasses:
            aAssociatedClass.removeAssociationClassManyKey(self)

