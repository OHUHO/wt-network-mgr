const chatData = [
    {
        id: 1,
        image: require('@/assets/images/users/avatar-2.jpg'),
        name: '修',
        message: 'hello！该起床了',
        time: '04    min',
        status: 'online'
    },
    {
        id: 2,
        image: require('@/assets/images/users/avatar-3.jpg'),
        name: '蠡县',
        message: '什么档次，还要我亲自做',
        time: '09 min',
        status: 'away'
    },
    {
        id: 3,
        name: '金毛',
        message: '困！！！',
        time: '21 min',
        status: 'online'
    },
    {
        id: 4,
        image: require('@/assets/images/users/avatar-4.jpg'),
        name: '嘎查',
        message: '很困',
        time: '1 hr',
    },
    {
        id: 5,
        name: '小韩',
        message: '好困',
        time: '3 hrs',
        status: 'away'
    },
    {
        id: 6,
        image: require('@/assets/images/users/avatar-6.jpg'),
        name: '米数',
        message: '想吃饭',
        time: '5 hrs',
        status: 'online'
    },
    {
        id: 7,
        image: require('@/assets/images/users/avatar-2.jpg'),
        name: '咔咔',
        message: '想睡觉',
        time: '24/03',
        status: 'online'
    },
];  

const chatMessagesData = [
    {
        name: '修',
        image: require('@/assets/images/users/avatar-2.jpg'),
        message: 'Hello!',
        time: '10:00'
    },
    {
        align: 'right',
        name: '李华',
        message: 'Hi,你今天上班吗',
        time: '10:02'
    },
    {
        name: '修',
        image: require('@/assets/images/users/avatar-2.jpg'),
        message: '困啊困啊',
        time: '10:06'
    },
    {
        name: '修',
        image: require('@/assets/images/users/avatar-2.jpg'),
        message: '这班，狗都不上',
        time: '10:06'
    },
    {
        align: 'right',
        name: '李华',
        message: 'Wow that\'s great',
        time: '10:07'
    }
];

export { chatData, chatMessagesData };