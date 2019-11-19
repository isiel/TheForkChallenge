# TheForkChallenge
This is a repository for the Android challenge for The Fork.

### Description

I used the MVP arquitecture. The are two screens: one with a list of restaurants and the other with the restaurant details.

#### Restaurant List

| List |
| -- |
| <img src="https://user-images.githubusercontent.com/10503925/69107259-86e3ed80-0a4f-11ea-812c-910fcb044419.png" width="300px" /> |

I used the 4 restaurant ids that you provided to create a list of restaurant to make reservations. You can tap on one restaurant to get the details from the repository.

#### Restaurant Details

This screen is drawn using the data retreived from the Fork API. 

| Details top | Details bottom |
|---|---|
| <img src="https://user-images.githubusercontent.com/10503925/69107264-89464780-0a4f-11ea-90fc-4d6588704d77.png" width="300px"/> | <img src="https://user-images.githubusercontent.com/10503925/69107265-8b100b00-0a4f-11ea-9a0a-c2af8505e1c1.png" width="300px" /> |

### Possible improvements

- Using MVP and saving the presenter instance the way I did it is deprecated by Google. The recommended way, according to Google in the javadocs of the method, is to use the MVVM arquitecture.
- I'm not an expert in Rx, so the way I used Rx to catch the callbacks of the API might not be the best.
- The DTO used to parse the API response can be improved maybe using GSON adapters to serialize / deserialize the JSON to make the class more clean and readable.
- Like I put in the comment, I couldn't test completely the Details presenter.
- Adding a viewpager dot indicator
- Unit Testing the activity class 


