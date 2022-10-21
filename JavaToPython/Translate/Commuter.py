from Seating import Seating

class Commuter():
    def __init__(self, aName):
        self._name = aName
        self._seatings = []

    def setName(self, aName):
        wasSet = False
        self._name = aName
        wasSet = True
        return wasSet

    def getName(self):
        return self._name

    def getSeating(self, index):
        aSeating = self._seatings[index]
        return aSeating

    def getSeatings(self):
        newSeatings = tuple(self._seatings)
        return newSeatings

    def numberOfSeatings(self):
        number = len(self._seatings)
        return number

    def hasSeatings(self):
        has = len(self._seatings) > 0
        return has

    def indexOfSeating(self, aSeating):
        index = self._seatings.index(aSeating)
        return index

    @staticmethod
    def minimumNumberOfSeatings():
        return 0

    def addSeating(self, aBus):
        return Seating(aBus, self)

    def addSeating(self, aSeating):
        wasAdded = False
        if aSeating in self._seatings :
            return False
        existingCommuter = aSeating.getCommuter()
        isNewCommuter = existingCommuter != None and not self.equals(existingCommuter)
        if isNewCommuter :
            aSeating.setCommuter(self)
        else :
            self._seatings.append(aSeating)
        wasAdded = True
        return wasAdded

    def removeSeating(self, aSeating):
        wasRemoved = False
        if not self.equals(aSeating.getCommuter()) :
            self._seatings.remove(aSeating)
            wasRemoved = True
        return wasRemoved

    def addSeatingAt(self, aSeating, index):
        wasAdded = False
        if self.addSeating(aSeating) :
            if index < 0 :
                index = 0
            if index > self.numberOfSeatings() :
                index = self.numberOfSeatings() - 1
            self._seatings.remove(aSeating)
            self._seatings.insert(index, aSeating)
            wasAdded = True
        return wasAdded

    def addOrMoveSeatingAt(self, aSeating, index):
        wasAdded = False
        if aSeating in self._seatings :
            if index < 0 :
                index = 0
            if index > self.numberOfSeatings() :
                index = self.numberOfSeatings() - 1
            self._seatings.remove(aSeating)
            self._seatings.insert(index, aSeating)
            wasAdded = True
        else :
            wasAdded = self.addSeatingAt(aSeating, index)
        return wasAdded

    def delete(self):
        i = len(self._seatings)
        while i > 0 :
            aSeating = self._seatings[i - 1]
            aSeating.delete()
            i = i - 1

    def __str__(self):
        return str(super()) + "[" + "name" + ":" + self.getName() + "]"

