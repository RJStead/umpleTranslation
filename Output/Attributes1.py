class Person :
    def __init__ (self, name, age):
        self.name = name
        self.age = age

    def set_name (self, _name):
        self.name = _name

    def set_age (self, _age):
        self.age = _age

    def get_name (self):
        return self.name

    def get_age (self):
        return self.age

    def __del__ (self):
        pass

