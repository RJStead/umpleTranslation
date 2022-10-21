from MentorC import MentorC

class MentorCSub(MentorC):
    def __init__(self, aName):
        super().__init__(aName)

    def delete(self):
        super().delete()

