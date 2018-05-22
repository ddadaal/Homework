#include <iostream>
using namespace std;

class DateTime {
private:
	int year;
	int month;
	int day;
	int hour;
	int minute;
	int second;
public:
	void runOneSecond(){
		second++;
		if (second == 60){
			second = 0;
			minute++;
			if (minute == 60) {
				minute = 0;
				hour++;
				if (hour == 24){
					hour = 0;
					day++;
					if (day == dayOfMonth()+1) {
						day = 1;
						month++;
						if (month == 13){
							month = 1;
							year++;
						}
					}
				}
			}
		}
	}
	int dayOfMonth() {
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 2:
			return isLeapYear() ? 29 : 28;
		default:
			return 30;
		}
	}
	bool isLeapYear() {
		return (year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0));
	}
	friend ostream& operator<<(ostream&, DateTime&);
	friend istream& operator>>(istream&, DateTime&);
	DateTime& operator++(int) {
		DateTime* old = new DateTime(*this);
		++(*this);
		return *old;
	}
	DateTime& operator++() {
		runOneSecond();
		return *this;
	}
};

ostream& operator<<(ostream& out, DateTime& datetime){
	out << datetime.year << " "
		<< datetime.month << " "
		<< datetime.day << " "
		<< datetime.hour << " "
		<< datetime.minute << " "
		<< datetime.second;
	return out;
}

istream& operator>>(istream& in, DateTime& datetime) {
	in >> datetime.year >> datetime.month
		>> datetime.day >> datetime.hour
		>> datetime.minute >> datetime.second;
	return in;
}

int main() {
	DateTime datetime;
	cin >> datetime;
	datetime++;
	cout << datetime;
}