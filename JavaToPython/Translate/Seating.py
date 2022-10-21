from Bus import Bus
from Commuter import Commuter
import os

class Seating():
    def __init__(self, aBus, aCommuter):
        self._seat = None
        didAddBus = self.setBus(aBus)
        if not didAddBus :
            raise RuntimeError ("Unable to create seating due to bus. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html")
        didAddCommuter = self.setCommuter(aCommuter)
        if not didAddCommuter :
            raise RuntimeError ("Unable to create seating due to commuter. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html")
        self._bus = None
        self._commuter = None

    def setSeat(self, aSeat):
        wasSet = False
        self._seat = aSeat
        wasSet = True
        return wasSet

    def getSeat(self):
        return self._seat

    def getBus(self):
        return self._bus

    def getCommuter(self):
        return self._commuter

    def setBus(self, aBus):
        wasSet = False
        if aBus is None :
            return wasSet
        existingBus = self._bus
        self._bus = aBus
        if existingBus != None and not existingBus.equals(aBus) :
            existingBus.removeSeating(self)
        self._bus.addSeating(self)
        wasSet = True
        return wasSet

    def setCommuter(self, aCommuter):
        wasSet = False
        if aCommuter is None :
            return wasSet
        existingCommuter = self._commuter
        self._commuter = aCommuter
        if existingCommuter != None and not existingCommuter.equals(aCommuter) :
            existingCommuter.removeSeating(self)
        self._commuter.addSeating(self)
        wasSet = True
        return wasSet

    def delete(self):
        placeholderBus = self._bus
        self._bus = None
        if not(placeholderBus is None) :
            placeholderBus.removeSeating(self)
        placeholderCommuter = self._commuter
        self._commuter = None
        if not(placeholderCommuter is None) :
            placeholderCommuter.removeSeating(self)

    def __str__(self):
        return str(super()) + "[" + "seat" + ":" + self.getSeat() + "]" + os.linesep + "  " + "bus = " + (self.getBus() != (format(id(self.getBus()), "x")) if None else "null") + os.linesep + "  " + "commuter = " + (self.getCommuter() != (format(id(self.getCommuter()), "x")) if None else "null")

