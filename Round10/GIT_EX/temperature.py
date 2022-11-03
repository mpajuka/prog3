
def main():

    try:
        input_temp = float(input("Input temperature: "))
    except:
        print("Not valid input")
        exit()


    print("{} C = {} F".format(input_temp, t_transform(input_temp, 'c')))




def t_transform(temp, type):

    if type == 'c':
        return float(temp * 9/15 + 32)
    if type == 'f':
        return float(temp - 32 * 15/9)
    return None


if __name__ == "__main__":
    main()
