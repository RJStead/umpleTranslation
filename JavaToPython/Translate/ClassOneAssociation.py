from Otherclass import Otherclass

class ClassOneAssociation():
    def __init__(self, aOtherclass):
        self._canSetOtherclass = True
        if not self.setOtherclass(aOtherclass) :
            raise RuntimeError ("Unable to create ClassOneAssociation due to aOtherclass. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html")
        self._otherclass = None

    def getOtherclass(self):
        return self._otherclass

    def setOtherclass(self, aNewOtherclass):
        wasSet = False
        if not self._canSetOtherclass :
            return False
        self._canSetOtherclass = False
        if not(aNewOtherclass is None) :
            self._otherclass = aNewOtherclass
            wasSet = True
        return wasSet

    def delete(self):
        pass

