from Seating import Seating

class Bus():
    def __init__(self, aNumber):
        self._number = aNumber
        self._seatings = []

    def setNumber(self, aNumber):
        wasSet = False
        self._number = aNumber
        wasSet = True
        return wasSet

    def getNumber(self):
        return self._number

    def getSeating(self, index):
        aSeating = self._seatings[index]
        return aSeating

    def getSeatings(self):
        newSeatings = tuple(self._seatings)
        return newSeatings

    def numberOfSeatings(self):
        self._number = len(self._seatings)
        return self._number

    def hasSeatings(self):
        has = len(self._seatings) > 0
        return has

    def indexOfSeating(self, aSeating):
        index = self._seatings.index(aSeating)
        return index

    @staticmethod
    def minimumNumberOfSeatings():
        return 0

    def addSeating(self, aCommuter):
        return Seating(self, aCommuter)

    def addSeating(self, aSeating):
        wasAdded = False
        if aSeating in self._seatings :
            return False
        existingBus = aSeating.getBus()
        isNewBus = existingBus != None and not self.equals(existingBus)
        if isNewBus :
            aSeating.setBus(self)
        else :
            self._seatings.append(aSeating)
        wasAdded = True
        return wasAdded

    def removeSeating(self, aSeating):
        wasRemoved = False
        if not self.equals(aSeating.getBus()) :
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
        return str(super()) + "[" + "number" + ":" + self.getNumber() + "]"

