#include <stdio.h>

// ANSI颜色码
#define RESET "\x1b[0m"
#define BLACK "\x1b[30m"
#define RED "\x1b[31m"
#define GREEN "\x1b[32m"
#define YELLOW "\x1b[33m"
#define BLUE "\x1b[34m"

#define MAGENTA "\x1b[35m"
#define CYAN "\x1b[36m"
#define WHITE "\x1b[37m"
#define BRIGHT_RED "\x1b[91m"

// ANSI样式码
#define BOLD "\x1b[1m"
#define UNDERLINE "\x1b[4m"
#define REVERSE "\x1b[7m"

void printHeading(const char *heading) {
    printf("%s%s%s\n", BOLD, GREEN, heading);
    printf("%s\n", RESET);
}

void printSubheading(const char *subheading) {
    printf("%s%s%s\n", BOLD, CYAN, subheading);
    printf("%s\n", RESET);
}

void printOption(int index, const char *option) {
    printf("%s%d.%s %s%s\n", YELLOW, index, RESET, WHITE, option);
}

void printSeparator() {
    printf("%s-----------------------------------%s\n", CYAN, RESET);
}