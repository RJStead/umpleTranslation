class Otherclass():
    def __init__(self, aName):
        self._name = aName

    def getName(self):
        return self._name

    def delete(self):
        pass

    def __str__(self):
        return str(super()) + "[" + "name" + ":" + self.getName() + "]"

