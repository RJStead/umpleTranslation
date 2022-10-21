from AssociatedClassWithKey import AssociatedClassWithKey

class AssociationClass():
    def __init__(self, aAssociatedClassWithKey):
        self._cachedHashCode = -1
        self._canSetAssociatedClassWithKey = True
        if aAssociatedClassWithKey is None or aAssociatedClassWithKey.getAssociationClass() != None :
            raise RuntimeError ("Unable to create AssociationClass due to aAssociatedClassWithKey. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html")
        self._associatedClassWithKey = aAssociatedClassWithKey

    def __init__(self, aIdForAssociatedClassWithKey):
        self._associatedClassWithKey = AssociatedClassWithKey(aIdForAssociatedClassWithKey, self)
        self._cachedHashCode = None
        self._canSetAssociatedClassWithKey = None

    def getAssociatedClassWithKey(self):
        return self._associatedClassWithKey

    def equals(self, obj):
        if obj is None :
            return False
        if not self.getClass().equals(obj.getClass()) :
            return False
        compareTo = obj
        if self.getAssociatedClassWithKey() is None and compareTo.getAssociatedClassWithKey() != None :
            return False
        elif self.getAssociatedClassWithKey() != None and not self.getAssociatedClassWithKey().equals(compareTo.getAssociatedClassWithKey()) :
            return False
        return True

    def hashCode(self):
        if self._cachedHashCode != -1 :
            return self._cachedHashCode
        self._cachedHashCode = 17
        if not(self.getAssociatedClassWithKey() is None) :
            self._cachedHashCode = self._cachedHashCode * 23 + self.getAssociatedClassWithKey().hashCode()
        else :
            self._cachedHashCode = self._cachedHashCode * 23
        self._canSetAssociatedClassWithKey = False
        return self._cachedHashCode

    def delete(self):
        existingAssociatedClassWithKey = self._associatedClassWithKey
        self._associatedClassWithKey = None
        if not(existingAssociatedClassWithKey is None) :
            existingAssociatedClassWithKey.delete()

