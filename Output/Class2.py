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

class House :
    def __init__ (self, address, price):
        self.address = address
        self.price = price

    def set_address (self, _address):
        self.address = _address

    def set_price (self, _price):
        self.price = _price

    def get_address (self):
        return self.address

    def get_price (self):
        return self.price

    def __del__ (self):
        pass

