# 1. 规定 CMake 的最低版本要求（CLion 会根据当前安装的版本自动填充，通常 3.15+ 即可）
cmake_minimum_required(VERSION 3.20)

# 2. 定义项目的名称（可以随便起，这里叫 HelloWorldProject）
project(HelloWorldProject LANGUAGES CXX)

include_directories(
        ${CMAKE_CURRENT_SOURCE_DIR}
)

# 3. 指定 C++ 标准（刷题通常推荐用 C++17 或 C++20）
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY "${CMAKE_SOURCE_DIR}/cmake-build-debug")


# 4. 核心：添加一个编译目标（Executable）
# 第一个参数是生成的可执行程序名称（由你决定）
# 后面紧跟参与编译的源文件列表（如果有多个文件，用空格隔开）
add_executable(linked_list _88_MergeSortedArray.cc)
add_executable(_560_SubArraySumEqualsK _560_SubArraySumEqualsK.cc)
add_executable(_56_MergeIntervals _56_MergeIntervals.cc)
add_executable(_13_RomanToInteger _13_RomanToInteger.cc)
add_executable(_5_LongestPalindromicSubstring dynamic_programming/_5_LongestPalindromicSubstring.cc)
add_executable(_141_LinkedListCycle linked_list/_141_LinkedListCycle.cc)
add_executable(_206_ReverseLinkedList linked_list/_206_ReverseLinkedList.cc)
add_executable(_2_AddTwoNumbers linked_list/_2_AddTwoNumbers.cc)
add_executable(_234_PalindromeLinkedList linked_list/_234_PalindromeLinkedList.cc)
add_executable(_143_ReorderList linked_list/_143_ReorderList.cc)
add_executable(_160_IntersectionOfTwoLinkedLists linked_list/_160_IntersectionOfTwoLinkedLists.cc)
add_executable(_61_RotateList linked_list/_61_RotateList.cc)
add_executable(_142_LinkedListCycle_2 linked_list/_142_LinkedListCycle_2.cc)
add_executable(_146_lru_cache _146_lru_cache.cc)
add_executable(_454_4Sum_2 hash/_454_4Sum_2.cc)
add_executable(_18_4Sum hash/_18_4Sum.cc)
add_executable(_20_ValidParentheses stack/_20_ValidParentheses.cc)
add_executable(_382_RansomNote hash/_382_RansomNote.cc)
add_executable(_594_LongestHarmoniousSubsequence hash/_594_LongestHarmoniousSubsequence.cc)
add_executable(_205_IsomorphicStrings hash/_205_IsomorphicStrings.cc)
add_executable(_290_WordPattern hash/_290_WordPattern.cc)
add_executable(_387_FirstUniqueCharInString hash/_387_FirstUniqueCharInString.cc)
add_executable(_202_HappyNumber hash/_202_HappyNumber.cc)
add_executable(_102_BinaryTreeLevelOrderTraversal tree/_102_BinaryTreeLevelOrderTraversal.cc)
add_executable(_94_BinaryTreeInorderTraversal tree/_94_BinaryTreeInorderTraversal.cc)
add_executable(_104_MaxDepthOfBinaryTree tree/_104_MaxDepthOfBinaryTree.cc)
add_executable(_543_DiamterOfBinaryTree tree/_543_DiamterOfBinaryTree.cc)
add_executable(_101_SymmetricTree tree/_101_SymmetricTree.cc)