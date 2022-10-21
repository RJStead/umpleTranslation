from Otherclass import Otherclass

class ClassOptionalOneAssociation():
    def __init__(self, aOtherclass):
        self._canSetOtherclass = True
        self.setOtherclass(aOtherclass)
        self._otherclass = None

    def getOtherclass(self):
        return self._otherclass

    def hasOtherclass(self):
        has = not(self._otherclass is None)
        return has

    def setOtherclass(self, aNewOtherclass):
        wasSet = False
        if not self._canSetOtherclass :
            return False
        self._canSetOtherclass = False
        self._otherclass = aNewOtherclass
        wasSet = True
        return wasSet

    def delete(self):
        pass

